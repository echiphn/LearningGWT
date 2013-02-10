
package com.manning.gwtip.user.client;

import com.google.gwt.user.client.rpc.SerializableException;

public class UserServiceException extends SerializableException {
    
    public UserServiceException() {
        super();
    }
    public UserServiceException(String message){
        super(message);
    }
    
}
