package com.manning.gwtip.antmaven.client;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface MyServiceAsync {
    public void sayHello(String s, AsyncCallback callback);
}
