package gwtip.sotu.client;

import com.google.gwt.user.client.History;

public class EntryPoint implements com.google.gwt.core.client.EntryPoint{

    public EntryPoint() {
        super();
    }
    
    public void onModuleLoad() {
        Controller c = Controller.getInstance();
        String token = History.getToken();
        if( token == null || !c.handleToken(token) ){
           c.login();
        }
    }
}
