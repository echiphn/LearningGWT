package com.manning.gwtip.testme.server;

import com.manning.gwtip.testme.client.MyService;
import com.manning.gwtip.testme.client.model.Person;

import java.util.ArrayList;
import java.util.List;


public class MyServiceImpl implements MyService {
    // mock up people persistence with a List (could be an ORM tool, DB straight, or another service, anything)
    private List people = new ArrayList();

    public MyServiceImpl() {
    }

    public void addOrUpdatePerson(Person p) {
        System.out.println("addOrUpdatePerson - " + p);

        if (!people.contains(p)) {
            people.add(p);
        }
    }

    public void addOrUpdatePersonSlow(Person p) {
        System.out.println("addOrUpdatePersonSlow - " + p);

        try {
            System.out.println("WAIT 3 SEC");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!people.contains(p)) {
            people.add(p);
        }
    }

    public void removePerson(Person p) {
        System.out.println("removePerson - " + p);

        if (people.contains(p)) {
            people.remove(p);
        }
    }

    public List listPeople() {
        System.out.println("listPeople");

        return people;
    }
}
