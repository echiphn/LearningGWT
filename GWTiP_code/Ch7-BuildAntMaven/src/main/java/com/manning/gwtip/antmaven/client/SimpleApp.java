package com.manning.gwtip.antmaven.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class SimpleApp implements EntryPoint {
    private MyServiceAsync service;

    public void onModuleLoad() {
        service = (MyServiceAsync) GWT.create(MyService.class);

        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "/MyService");

        final Label inputLabel = new Label(
                "Enter your name to say hello to the server:");
        final TextBox input = new TextBox();
        final Button submit = new Button("Submit");
        final Label output = new Label();

        submit.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    service.sayHello(input.getText(),
                        new AsyncCallback() {
                            public void onSuccess(Object result) {
                                String r = (String) result;
                                output.setText(r);
                            }

                            public void onFailure(Throwable failure) {
                                Window.alert("ERROR - " + failure.getMessage());
                            }
                        });
                }
            });

        RootPanel.get().add(inputLabel);
        RootPanel.get().add(input);
        RootPanel.get().add(submit);
        RootPanel.get().add(output);
    }
}
