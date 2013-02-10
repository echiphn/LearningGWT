package com.manning.gwtip.user.client;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.io.Serializable;


public class Address implements IsSerializable, Serializable {
    private Long id;
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private String city;
    private String postalCode;
    private String state;
    private String street1;
    private String street2;

    public Address() {
        super();
    }

    public void setCity(String city) {
        String old = this.city;
        this.city = city;
        changes.firePropertyChange("city", old, city);
    }

    public String getCity() {
        return city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPostalCode(String postalCode) {
        String old = this.postalCode;
        this.postalCode = postalCode;
        changes.firePropertyChange("postalCode", old, postalCode);
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setState(String state) {
        String old = this.state;
        this.state = state;
        changes.firePropertyChange("state", old, state);
    }

    public String getState() {
        return state;
    }

    public void setStreet1(String street1) {
        String old = this.street1;
        this.street1 = street1;
        changes.firePropertyChange("street1", old, street1);
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet2(String street2) {
        String old = this.street2;
        this.street2 = street2;
        changes.firePropertyChange("street2", old, street2);
    }

    public String getStreet2() {
        return street2;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void addPropertyChangeListener(String propertyName,
        PropertyChangeListener l) {
        changes.addPropertyChangeListener(propertyName, l);
    }

    public PropertyChangeListener[] allPropertyChangeListeners() {
        return changes.getPropertyChangeListeners();
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public void removePropertyChangeListener(String propertyName,
        PropertyChangeListener l) {
        changes.removePropertyChangeListener(propertyName, l);
    }
}
