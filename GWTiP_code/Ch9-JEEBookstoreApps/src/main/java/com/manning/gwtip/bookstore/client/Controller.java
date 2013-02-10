
package com.manning.gwtip.bookstore.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.manning.gwtip.bookstore.client.model.Book;
import com.manning.gwtip.bookstore.client.remote.BookstoreService;
import com.manning.gwtip.bookstore.client.remote.BookstoreServiceAsync;

import java.util.List;

public class Controller {
    
    private static Controller instance;
    private BookstoreServiceAsync service =
            (BookstoreServiceAsync) GWT.create( BookstoreService.class );
    private  ModelState model = ModelState.getInstance();
    
    private Controller() {
        super();
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "BookstoreService");
    }
    
    public static Controller getInstance(){
        return instance == null ? instance = new Controller() : instance;
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
    
    public void deleteBook( Book b ){
        service.deleteBook( b, new AsyncCallback(){
            public void onSuccess(Object object) {
                findAllBooks();
            }

            public void onFailure(Throwable throwable) {
                Window.alert( "Failure");
                GWT.log( "Exception from service", throwable);
            }
            
        });
    }
    
    public void storeBook( Book book ){
        service.storeBook( book, new AsyncCallback(){
            public void onSuccess(Object result) {
                Window.alert( "Success");
                findAllBooks();
            }

            public void onFailure(Throwable caught) {
                Window.alert( "Failure");
                GWT.log( "Exception from service", caught);
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
