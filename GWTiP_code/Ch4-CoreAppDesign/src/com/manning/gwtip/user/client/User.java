package com.manning.gwtip.user.client;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.io.Serializable;


public class User implements IsSerializable, Serializable {
    private Address billingAddress = new Address();
    private Address shippingAddress = new Address();
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private String firstName;
    private String lastName;
    private String notes;
    private String password;
    private String username;

    public User() {
        super();
    }

    public void setBillingAddress(Address billingAddress) {
        Address old = this.billingAddress;
        this.billingAddress = billingAddress;
        changes.firePropertyChange("billingAddress", old, billingAddress);
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setFirstName(String firstName) {
        String old = this.firstName;
        this.firstName = firstName;
        changes.firePropertyChange("firstName", old, firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        String old = lastName;
        this.lastName = lastName;
        changes.firePropertyChange("lastName", old, lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setNotes(String notes) {
        String old = this.notes;
        this.notes = notes;
        changes.firePropertyChange("notes", old, notes);
    }

    public String getNotes() {
        return notes;
    }

    public void setPassword(String password) {
        String old = this.password;
        this.password = password;
        changes.firePropertyChange("password", old, password);
    }

    public String getPassword() {
        return password;
    }

    public void setShippingAddress(Address shippingAddress) {
        Address old = this.shippingAddress;
        this.shippingAddress = shippingAddress;
        changes.firePropertyChange("shippingAddress", old, shippingAddress);
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setUsername(String username) {
        String old = this.username;
        this.username = username;
        changes.firePropertyChange("username", old, username);
    }

    public String getUsername() {
        return username;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void addPropertyChangeListener(String propertyName,
        PropertyChangeListener l) {
        changes.addPropertyChangeListener(propertyName, l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public void removePropertyChangeListener(String propertyName,
        PropertyChangeListener l) {
        changes.removePropertyChangeListener(propertyName, l);
    }
}
