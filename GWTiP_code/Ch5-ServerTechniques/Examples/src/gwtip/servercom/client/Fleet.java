

package gwtip.servercom.client;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;

public class Fleet implements IsSerializable{
    
    public String name;
    /**
     * @gwt.typeArgs <gwtip.servercom.client.Vessel>
     */
    public ArrayList vessels;
    
    public Fleet() {
        super();
    }
    
}
