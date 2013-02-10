package com.manning.gwtip.helloserver.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.manning.gwtip.helloserver.client.HelloService;
import com.manning.gwtip.helloserver.client.Person;


public class HelloServlet extends RemoteServiceServlet implements HelloService {
    private HelloService impl = new HelloServiceImpl();

    public HelloServlet() {
        super();
    }

    public String sayHello(Person p) {
        return impl.sayHello(p);
    }
}
