/*
 * FileUploadServlet.java
 *
 * Created on March 7, 2007, 3:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manning.gwtip.bookstore.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author cooper
 */
public class FileUploadServlet extends HttpServlet{
    
    private static final HashMap TYPE_MAPS = new HashMap();
    static{
        TYPE_MAPS.put( "image/jpeg", "jpeg");
        TYPE_MAPS.put( "image/gif", "gif" );
        TYPE_MAPS.put( "image/png", "png" );
        TYPE_MAPS.put( "image/jpg", "jpeg" );
        TYPE_MAPS.put( "application/jpg", "jpeg");
        TYPE_MAPS.put( "application/x-jpg", "jpeg");
    }
    
    
    /** Creates a new instance of FileUploadServlet */
    public FileUploadServlet() {
        super();
    }
    
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if( !isMultipart){
            response.getWriter().println( "Not multipart.");
            response.setStatus(response.SC_EXPECTATION_FAILED);
            return;
        }
        try{
            
            
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            System.out.println( items.size() );
            for( FileItem item: items){
                System.out.println( item.getName() );
                String ext = (String) TYPE_MAPS.get( item.getContentType() );
                if( ext == null ){
                    response.setStatus(response.SC_EXPECTATION_FAILED);
                    return;
                }
                
                String path = "/images/" + System.currentTimeMillis() +"."+ext;
                try{
                    FileOutputStream fos = new FileOutputStream( this.getServletContext().getRealPath(path));
                    copyStream( item.getInputStream(), fos );
                    fos.flush();
                    fos.close();
                } catch(IOException e){
                    this.log( "Exception writing file", e);
                    throw new ServletException( e );
                }
                response.getWriter().print( path );
                return;
                
            }
        } catch( FileUploadException fue){
            this.log( "Exception on upload", fue);
            response.setStatus( response.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.getWriter().println("No file upload found.");
        response.setStatus(response.SC_EXPECTATION_FAILED);
        return;
    }
    
    
    
    
    public static int copyStream(InputStream sourceStream,OutputStream destinationStream) throws IOException {
        int bytesRead = 0;
        int totalBytes = 0;
        byte[] buffer = new byte[1024];
        
        while(bytesRead >= 0) {
            bytesRead = sourceStream.read(buffer,0,buffer.length);
            
            if(bytesRead > 0) {
                destinationStream.write(buffer,0,bytesRead);
            }
            
            totalBytes += bytesRead;
        }
        
        return totalBytes;
    }
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        new File(config.getServletContext().getRealPath("/images")).mkdirs();
        
    }
    
    
}
