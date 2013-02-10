/*
 * LeaveMessage.java
 *
 * Created on October 7, 2006, 5:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gwtip.sotu.server;

/**
 *
 * @author cooper
 */
public class LeaveMessage extends Message{
    
    /** Creates a new instance of LeaveMessage */
    public LeaveMessage(User user, Conversation conversation) {
        this.time =  System.currentTimeMillis() - conversation.startTime ;
        StringBuffer message = new StringBuffer("<leave>");
        message.append("<user>"); 
        message.append(user.username);
        message.append("</user>");
        message.append("<time>");
        message.append(this.time );
        message.append("</time>");
        message.append("<value>-Goodbye.</value>");
        message.append("</leave>");
        this.message = message.toString();
        this.conversation = conversation.descriptor.name;
    }
    
}
