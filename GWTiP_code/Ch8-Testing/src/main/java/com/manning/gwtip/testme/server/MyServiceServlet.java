package com.manning.gwtip.testme.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.manning.gwtip.testme.client.MyService;
import com.manning.gwtip.testme.client.model.Person;

import java.util.ArrayList;
import java.util.List;


public class MyServiceServlet extends RemoteServiceServlet implements MyService {
    
    private MyService impl = new MyServiceImpl();

    public MyServiceServlet() {
    }

    protected void onBeforeRequestDeserialized(java.lang.String serializedRequest)
    {
        System.out.println("serializedRequest - " + serializedRequest);        
    }
    
    public void addOrUpdatePerson(Person p) {
        impl.addOrUpdatePerson(p);
    }

    public void addOrUpdatePersonSlow(Person p) {
        impl.addOrUpdatePersonSlow(p);
    }

    public void removePerson(Person p) {
        impl.removePerson(p);
    }

    /**
     * @gwt.typeArgs <com.manning.gwtip.testme.client.model.Person>
     */
    public List listPeople() {
        return impl.listPeople();
    }
}
