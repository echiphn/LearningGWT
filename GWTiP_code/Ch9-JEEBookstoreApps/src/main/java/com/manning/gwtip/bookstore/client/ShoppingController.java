
package com.manning.gwtip.bookstore.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.manning.gwtip.bookstore.client.model.Book;
import com.manning.gwtip.bookstore.client.remote.CustomerService;
import com.manning.gwtip.bookstore.client.remote.CustomerServiceAsync;

import java.util.List;

public class ShoppingController {
    
    private static ShoppingController instance;
    private CustomerServiceAsync service =
            (CustomerServiceAsync) GWT.create( CustomerService.class );
    private  ModelState model = ModelState.getInstance();
    
    private ShoppingController() {
        super();
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "/CustomerService");
    }
    
    public static ShoppingController getInstance(){
        return instance == null ? instance = new ShoppingController() : instance;
    }
    
    
    
    public void updateCategories(){
        service.findAllCategories( new AsyncCallback(){
            public void onSuccess(Object result) {
                model.setCategories( (List) result );
            }

            public void onFailure(Throwable caught) {
                Window.alert( "There was an error retrieving the category list.");
                GWT.log( "Exception from service", caught);
            }
            
        });
    }
    
    public void updateAuthors(String firstName, String lastName){
        service.findAuthorsByName( firstName, lastName, new AsyncCallback(){
            public void onSuccess(Object result) {
                model.setAuthors( (List) result );
            }

            public void onFailure(Throwable caught) {
                Window.alert( "There was an error retrieving the author list.");
            }
            
        });
    }
    
    
    public void findAllAuthors(){
        service.findAllAuthors( new AsyncCallback(){
            public void onSuccess(Object object) {
                model.setAuthors( (List) object );
            }

            public void onFailure(Throwable throwable) {
                GWT.log( "Exception getting books", throwable);
                Window.alert( "Failed to retrieve author list.");
            }
            
        });
    }
    
    public void findAllBooks(){
        service.findAllBooks( new AsyncCallback(){
            public void onSuccess(Object object) {
                model.setBooks( (List) object );
            }

            public void onFailure(Throwable throwable) {
                GWT.log( "Exception getting books", throwable);
                Window.alert( "Failed to retrieve book list.");
            }
             
        });
    }
}
