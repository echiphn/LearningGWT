package gwtip.servercom.client;

import com.google.gwt.user.client.rpc.RemoteService;
import java.util.HashMap;
import java.util.Set;

public interface FleetRemoteService extends RemoteService {
    
    /**
     * @gwt.typeArgs fleets <gwtip.servercom.client.Fleet>
     */
    public void setFleetsForOwner( String owner, Set fleets);
    
    /**
     * @gwt.typeArgs <java.lang.String, gwtip.servercom.client.Fleet>
     */
    public HashMap getAllFleets();
    
}
