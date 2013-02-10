package gwtip.sotu.server;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    public SessionListener() {
        super();
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        User u = (User) httpSessionEvent.getSession().getAttribute( StreamServlet.USER );
        ConversationServiceServlet.service.signOff(u);
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ;//Do nothing.
    
    }
}
