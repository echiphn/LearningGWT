/*
 * Images.java
 *
 * Created on June 21, 2007, 7:48 PM
 */

package gwtip.sotu.server;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.io.CopyUtils;

/**
 *
 * @author cooper
 * @version
 */
public class Images extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("image/png");
        FileInputStream fis = new FileInputStream( this.getServletContext().getRealPath("/images/"+request.getPathTranslated() ));
        CopyUtils.copy( fis, response.getOutputStream() );
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
}
