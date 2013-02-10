package com.manning.gwtip.helloserver.server;

import com.manning.gwtip.helloserver.client.HelloService;
import com.manning.gwtip.helloserver.client.Person;


public class HelloServiceImpl implements HelloService {
    public HelloServiceImpl() {
    }

    public String sayHello(Person p) {
        return "Hello " + p.name + ". How is the weather at " + p.address +
        "?";
    }
}
