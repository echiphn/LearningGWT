/*
 * JoinMessage.java
 *
 * Created on October 7, 2006, 5:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gwtip.sotu.server;

/**
 *
 * @author cooper
 */
public class JoinMessage extends Message {
    
    /** Creates a new instance of JoinMessage */
    public JoinMessage(User user, Conversation conversation) {
        this.time= System.currentTimeMillis() - conversation.startTime;
        StringBuffer message = new StringBuffer("<join>");
        message.append("<user>");
        message.append(user.username);
        message.append("</user>");
        message.append("<time>");
        message.append( this.time  );
        message.append("</time>");
        message.append("<value>Welcome!</value>");
        message.append("</join>");
        this.message = message.toString();
        this.conversation = conversation.descriptor.name;
    }
    
}
