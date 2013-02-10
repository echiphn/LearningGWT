package com.manning.gwtip.testme.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import com.manning.gwtip.testme.client.MyService;
import com.manning.gwtip.testme.client.MyServiceAsync;
import com.manning.gwtip.testme.client.model.PeopleModelData;
import com.manning.gwtip.testme.client.model.Person;

import java.util.List;


public class Controller {
    private MyServiceAsync service;
    private PeopleModelData peopleData;

    public Controller(PeopleModelData pd) {
        service = (MyServiceAsync) GWT.create(MyService.class);

        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "/MyService");

        this.peopleData = pd;
    }

    //
    // Controller methods that invoke service calls should be void, and should deal
    // with client data objects that are Observable (and the view can observe when async updates come in)
    //
    public void addOrUpdatePerson(Person p) {
        service.addOrUpdatePerson(p,
            new AsyncCallback() {
                public void onSuccess(Object result) {
                    updatePeopleModelData();
                }

                public void onFailure(Throwable failure) {
                    Window.alert("ERROR addOrUpdatePerson - " +
                        failure.getMessage());
                }
            });
    }

    public void addOrUpdatePersonSlow(Person p) {
        service.addOrUpdatePersonSlow(p,
            new AsyncCallback() {
                public void onSuccess(Object result) {
                    updatePeopleModelData();
                }

                public void onFailure(Throwable failure) {
                    Window.alert("ERROR addOrUpdatePerson - " +
                        failure.getMessage());
                }
            });
    }

    public void removePerson(Person p) {
        service.removePerson(p,
            new AsyncCallback() {
                public void onSuccess(Object result) {
                    updatePeopleModelData();
                }

                public void onFailure(Throwable failure) {
                    Window.alert("ERROR removePerson - " +
                        failure.getMessage());
                }
            });
    }

    // widget does not call this
    // the SERVER model is authoritative, so we call this internal to the controller
    // and the widget observes the PeopleModelData as we change it
    private void updatePeopleModelData() {
        service.listPeople(new AsyncCallback() {
                public void onSuccess(Object result) {
                    peopleData.setPeople((List) result);
                }

                public void onFailure(Throwable failure) {
                    Window.alert("ERROR listPeople - " + failure.getMessage());
                }
            });
    }
}
