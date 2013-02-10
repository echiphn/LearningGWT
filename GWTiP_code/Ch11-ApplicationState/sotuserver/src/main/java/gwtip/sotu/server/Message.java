/*
 * Message.java
 *
 * Created on October 6, 2006, 11:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gwtip.sotu.server;

/**
 *
 * @author cooper
 */
public class Message {
    
    public String conversation;
    public String message;
    public long time;
    
    /** Creates a new instance of Message */
    public Message() {
        super();
    }
    
    public String toString(){
        return "["+conversation+"] "+message;
    }
    
}
