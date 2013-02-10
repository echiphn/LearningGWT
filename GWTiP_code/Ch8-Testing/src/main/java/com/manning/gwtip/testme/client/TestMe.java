package com.manning.gwtip.testme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class TestMe implements EntryPoint {
    public void onModuleLoad() {
        PersonFormWidget personForm = new PersonFormWidget();

        RootPanel.get().add(personForm);
    }
}
