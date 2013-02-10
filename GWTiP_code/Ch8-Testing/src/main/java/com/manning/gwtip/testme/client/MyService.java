package com.manning.gwtip.testme.client;

import com.google.gwt.user.client.rpc.RemoteService;

import com.manning.gwtip.testme.client.model.Person;

import java.util.List;


public interface MyService extends RemoteService {
    
    public void addOrUpdatePerson(Person p);

    public void addOrUpdatePersonSlow(Person p);

    public void removePerson(Person p);

    /**
     * @gwt.typeArgs <com.manning.gwtip.testme.client.model.Person>
     */
    public List listPeople();
}
