package com.manning.gwtip.testme.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.manning.gwtip.testme.client.model.Person;


public interface MyServiceAsync {
    
    public void addOrUpdatePerson(Person p, AsyncCallback callback);

    public void addOrUpdatePersonSlow(Person p, AsyncCallback callback);

    public void removePerson(Person p, AsyncCallback callback);

    public void listPeople(AsyncCallback callback);
}
