package gwtip.sotu.server;

import gwtip.sotu.client.remote.AccessException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StreamServlet extends HttpServlet {
    
    
    
    public static final String USER = "User";
    
   public StreamServlet() {
        super();
    }
    
    private void writeAndFlush( OutputStream out, String value ) 
    throws IOException {
        out.write(value.getBytes());
        out.write(new byte[500%value.length()]);
        out.flush();
    }
    
    
    public void service(ServletRequest req, ServletResponse res)
    throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        ServletContext application = this.getServletContext();
        HttpSession session = request.getSession();
       
        res.setContentType("text/html;charset=UTF-8");
        OutputStream out = res.getOutputStream();
        this.writeAndFlush( out, "<html>");
        
        User user = (User) session.getAttribute(StreamServlet.USER);
        if( user == null ){
            user = new User( request.getParameter("username"));
            System.out.println("Setting user");
            session.setAttribute( StreamServlet.USER, user );
        }
        
        try{
            ConversationServiceServlet.service.joinConversation( 
                    user,
                    ConversationServiceServlet.service.KEEP_ALIVE );
        } catch(AccessException ae){
            ;// this should never happen;
        }
        while(true){    
            try{
                Message m = (Message) user.sendQueue.poll();
                
                while( m != null ){
                    
                    StringBuffer write =
                            new StringBuffer("<script type=\"text/javascript\">" );
                    write.append("\twindow.parent.cometCallback('"+
                            m.conversation+"',unescape('"+
                            URLEncoder.encode(m.message, "UTF-8")
                            .replaceAll("\\x2B","%20")+
                            "'));\n");
                    write.append( "</script>\n");
                    this.writeAndFlush( out, write.toString() );
                    m = (Message) user.sendQueue.poll();
                }
                
            }catch(IOException e){
                e.printStackTrace();
                throw e;
            }
            try{
                Thread.sleep( 1000 );
            } catch(InterruptedException e){
                ;
            }
        }
       
    }
    
    
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);   
        try{
            ConversationServiceServlet.service.init( 
                    config.getServletContext().getRealPath("/") );
        }catch(IOException ioe){
            throw new ServletException("Unable to init directories", ioe);
        }
    }
    
    public void destroy() {
        super.destroy();
       
    }
}
