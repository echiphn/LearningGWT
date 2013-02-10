/*
 * Conversation.java
 *
 * Created on October 1, 2006, 10:14 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gwtip.sotu.client.remote;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author rcooper
 */
public class ConversationDescriptor implements IsSerializable {
    
    public String name;
    public String adminPassword;
    public String joinPassword;
    public long id;
    
    /** Creates a new instance of Conversation */
    public ConversationDescriptor() {
        super();
    }
    
    public ConversationDescriptor( String name, String adminPassword, String joinPassword ){
        super();
        this.name = name;
        this.adminPassword = adminPassword;
        this.joinPassword = joinPassword;
    }
    
}
