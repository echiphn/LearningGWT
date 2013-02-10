package com.manning.gwtip.bookstore.service;

import com.google.gwt.user.client.rpc.SerializableException;

public class BookstoreServiceException extends Exception{
    
    public BookstoreServiceException() {
        super();
    }
    public BookstoreServiceException(String message){
        super(message);
    }
    
    public BookstoreServiceException( String message, Throwable cause){
        super( message, cause );
    }
    
}
