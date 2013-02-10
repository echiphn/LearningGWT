package com.manning.gwtip.antmaven.client;

import com.google.gwt.user.client.rpc.RemoteService;


public interface MyService extends RemoteService {
    public String sayHello(String s);
}
