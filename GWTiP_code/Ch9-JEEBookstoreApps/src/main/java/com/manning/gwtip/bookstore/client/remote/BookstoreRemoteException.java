package com.manning.gwtip.bookstore.client.remote;

import com.google.gwt.user.client.rpc.SerializableException;

public class BookstoreRemoteException extends SerializableException{
    
    public BookstoreRemoteException() {
        super();
    }
    public BookstoreRemoteException(String message){
        super(message);
    }
}
