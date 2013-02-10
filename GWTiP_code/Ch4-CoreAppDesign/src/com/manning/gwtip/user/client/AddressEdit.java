package com.manning.gwtip.user.client;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;


class AddressEdit extends FlexTable {
    private Address address;
    private ListBox state = new ListBox();
    private TextBox city = new TextBox();
    private TextBox postalCode = new TextBox();
    private TextBox street1 = new TextBox();
    private TextBox street2 = new TextBox();
    private PropertyChangeListener[] listeners = new PropertyChangeListener[5];

    public AddressEdit(final Address address) {
        super();

        state.addItem("...");
        state.addItem("AL");
        state.addItem("AK");
        state.addItem("AR");
        state.addItem("CA");
        state.addItem("CT");
        state.addItem("DE");
        state.addItem("GA");

        this.setStyleName("user-AddressEdit");
        this.address = address;
        this.setWidget(0, 0, new Label("Street"));
        this.setWidget(0, 1, street1);
        this.setWidget(1, 0, new Label("Street"));
        this.setWidget(1, 1, street2);
        this.setWidget(2, 0, new Label("City, State"));

        HorizontalPanel hp = new HorizontalPanel();
        hp.add(city);
        hp.add(new Label(","));
        hp.add(state);
        this.setWidget(2, 1, hp);

        this.setWidget(3, 0, new Label("PostalCode"));
        this.setWidget(3, 1, postalCode);

        listeners[0] = new PropertyChangeListenerProxy(
                "street1",
                new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        street1.setText(
                            (String) propertyChangeEvent.getNewValue());
                    }
                });
        address.addPropertyChangeListener(listeners[0]);
        street1.addChangeListener(
            new ChangeListener() {
                public void onChange(Widget sender) {
                    address.setStreet1(street1.getText());
                }
            });

        listeners[1] = new PropertyChangeListenerProxy(
                "street2",
                new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        street2.setText(
                            (String) propertyChangeEvent.getNewValue());
                    }
                });
        address.addPropertyChangeListener(listeners[1]);
        street1.addChangeListener(
            new ChangeListener() {
                public void onChange(Widget sender) {
                    address.setStreet1(street1.getText());
                }
            });

        listeners[2] = new PropertyChangeListenerProxy(
                "city",
                new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        city.setText(
                            (String) propertyChangeEvent.getNewValue());
                    }
                });
        address.addPropertyChangeListener(listeners[2]);
        street1.addChangeListener(
            new ChangeListener() {
                public void onChange(Widget sender) {
                    address.setCity(city.getText());
                }
            });

        listeners[3] = new PropertyChangeListenerProxy(
                "postalCode",
                new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        postalCode.setText(
                            (String) propertyChangeEvent.getNewValue());
                    }
                });
        address.addPropertyChangeListener(listeners[3]);
        street1.addChangeListener(
            new ChangeListener() {
                public void onChange(Widget sender) {
                    address.setPostalCode(postalCode.getText());
                }
            });

        listeners[4] = new PropertyChangeListenerProxy(
                "state",
                new PropertyChangeListener() {
                    public void propertyChange(
                        PropertyChangeEvent propertyChangeEvent) {
                        for(int i = 0; i < state.getItemCount(); i++) {
                            if(
                                state.getItemText(i)
                                         .equals(
                                        propertyChangeEvent.getNewValue())) {
                                state.setSelectedIndex(i);

                                break;
                            }
                        }
                    }
                });
        address.addPropertyChangeListener(listeners[4]);
        state.addChangeListener(
            new ChangeListener() {
                public void onChange(Widget sender) {
                    String value = state.getItemText(state.getSelectedIndex());

                    if(!value.equals("...")) {
                        address.setState(value);
                    }
                }
            });
    }

    public void cleanup() {
        for(int i = 0; i < listeners.length; i++) {
            address.removePropertyChangeListener(listeners[i]);
        }
    }
}
