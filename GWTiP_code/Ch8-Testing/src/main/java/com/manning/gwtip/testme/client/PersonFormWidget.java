package com.manning.gwtip.testme.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtip.testme.client.controller.Controller;
import com.manning.gwtip.testme.client.model.PeopleModelData;
import com.manning.gwtip.testme.client.model.Person;


public class PersonFormWidget extends Composite {
    final Controller controller;
    final PeopleModelData peopleData;
    final Label personLabel;
    final Label firstLabel;
    final Label lastLabel;
    final TextBox firstInput;
    final TextBox lastInput;
    final Button submit;
    FlexTable table;
    Panel panel;

    public PersonFormWidget() {
        peopleData = new PeopleModelData();
        controller = new Controller(peopleData);

        personLabel = new Label("Please register a person.");
        firstLabel = new Label("First Name:");
        lastLabel = new Label("Last Name:");
        firstInput = new TextBox();
        lastInput = new TextBox();
        submit = new Button("Submit");

        table = new FlexTable();
        table.setWidget(0, 0, personLabel);
        table.getFlexCellFormatter().setColSpan(0, 0, 2);
        table.setWidget(1, 0, firstLabel);
        table.setWidget(1, 1, firstInput);
        table.setWidget(2, 0, lastLabel);
        table.setWidget(2, 1, lastInput);
        table.setWidget(3, 0, submit);
        table.getFlexCellFormatter().setColSpan(3, 0, 2);

        // submit button invokes controller addOrUpdatePerson
        submit.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    Person person = new Person(firstInput.getText(),
                            lastInput.getText());
                    controller.addOrUpdatePerson(person);

                    firstInput.setText("");
                    lastInput.setText("");
                }
            });

        // display of current people
        final Panel peopleDisplay = new VerticalPanel();
        peopleDisplay.add(new Label("Current people registered:"));
        
        peopleData.addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    peopleDisplay.clear();
                    peopleDisplay.add(new Label("Current people registered:"));

                    List people = (List) e.getNewValue();

                    for (int i = 0; i < people.size(); i++) {
                        Person p = (Person) people.get(i);
                        peopleDisplay.add(new Label(p.getFirstName() + " " +
                                p.getLastName()));
                    }
                }
            });

        panel = new VerticalPanel();
        panel.add(table);
        panel.add(new HTML("<br />"));
        panel.add(peopleDisplay);

        this.initWidget(panel);
    }
}
