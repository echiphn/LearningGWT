package gwtip.sotu.server;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.CopyUtils;


public class ImagePublishingServlet extends HttpServlet {
    
    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean isMultipart = ServletFileUpload
                .isMultipartContent(request);
        if( !isMultipart ){
            throw new ServletException("What are you thinking?");
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        try{
            List<FileItem> items = upload.parseRequest(request);
            String conversation = null;
            String adminPassword = null;
            FileItem image = null;
            for( FileItem item : items ){
                if( item.isFormField() && 
                        item.getFieldName().equals("adminPassword" ) ){
                    adminPassword = item.getString();
                } else if( item.isFormField() &&
                        item.getFieldName().equals("conversation") ){
                    conversation = item.getString();
                } else {
                    image = item;
                }
            }
            if( conversation == null || 
                    adminPassword == null ||
                    image == null ){
                throw new ServletException("No data.");
            }
            String filename = "/images/"+ 
                    System.currentTimeMillis() +
                    ".png";
            FileOutputStream fos = new FileOutputStream(
                    this.getServletContext()
                    .getRealPath(filename) );
            CopyUtils.copy( image.getInputStream(), fos );
            Conversation conv = ConversationServiceServlet
                    .service.conversations
                    .get( conversation );
        if( adminPassword.equals( adminPassword ) ){
            ConversationServiceServlet.service.sendImageMessage( conv.descriptor, 
                    request.getContextPath()+
                    "/"+filename );
            out.println("OK");
            out.close();
        } else {
            response.setStatus( response.SC_FORBIDDEN );
            new File( this.getServletContext()
                    .getRealPath(filename) ).delete();
            out.close();
        }
        } catch( Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        new File( config.getServletContext().getRealPath("/images/") ).mkdirs();
    }
}
