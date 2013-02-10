package com.manning.gwtip.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
public class UserCreateListenerImpl implements UserCreateListener{
    
     
    private UserServiceAsync service = (UserServiceAsync) GWT.create( UserService.class );
    
    public UserCreateListenerImpl() {
        super();
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL()+"UserService");
    }
    
    
    
    public void createUser(User user){
        if( user.getUsername().equals("root") ){
            Window.alert( "You can't be root!");
        }
        service.createUser( user, new AsyncCallback(){
            public void onSuccess(Object result) {
                Window.alert( "User created.");
                // here we would change the view to a new state.
            }

            public void onFailure(Throwable caught) {
                Window.alert( caught.getMessage() );
            }
            
        });
    
    }
    
}
