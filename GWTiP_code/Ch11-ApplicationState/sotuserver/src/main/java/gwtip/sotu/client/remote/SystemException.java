package gwtip.sotu.client.remote;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializableException;



/**
 *
 * @author cooper
 */
public class SystemException extends SerializableException {
    
    public SystemException(){
        super();
    }
    
    /** Creates a new instance of SystemException */
    public SystemException(String message ) {
        super(message);
    }
    
}
