/*
 * AccessException.java
 *
 * Created on October 1, 2006, 11:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gwtip.sotu.client.remote;

import com.google.gwt.user.client.rpc.SerializableException;

/**
 *
 * @author rcooper
 */
public class AccessException extends SerializableException{
    
    /** Creates a new instance of AccessException */
    public AccessException() {
        super();
    }
    
    public AccessException(String message){
        super(message);
    }
    
}
