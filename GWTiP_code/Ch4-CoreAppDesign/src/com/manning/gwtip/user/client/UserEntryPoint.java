package com.manning.gwtip.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserEntryPoint implements EntryPoint {
    
    /** Creates a new instance of UserEntryPoint */
    public UserEntryPoint() {
        super();
    }

    public void onModuleLoad() {
        User u = new User();
        VerticalPanel p = new VerticalPanel();
        p.setWidth( "100%");
        p.setHeight( "100%");
        p.add(   new UserEdit( new UserCreateListenerImpl(), u )  );
        RootPanel.get().add( p );
    }
    
    
    
}
