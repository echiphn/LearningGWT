package com.manning.gwtip.antmaven.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.manning.gwtip.antmaven.client.MyService;


public class MyServiceImpl extends RemoteServiceServlet implements MyService {
    public MyServiceImpl() {
    }

    public String sayHello(String s) {
        return "Hello from the server side " + s;
    }
}
