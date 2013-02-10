package com.manning.gwtip.bookstore.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Author implements IsSerializable {
    private java.lang.String bio;
    private java.lang.String firstName;
    private java.lang.Integer id;
    private java.lang.String lastName;
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(
            this);

    public void setBio(java.lang.String newValue) {
        java.lang.String oldValue = this.bio;
        this.bio = newValue;
        this.changes.firePropertyChange("bio", oldValue, newValue);
    }

    public java.lang.String getBio() {
        return this.bio;
    }

    public void setFirstName(java.lang.String newValue) {
        java.lang.String oldValue = this.firstName;
        this.firstName = newValue;
        this.changes.firePropertyChange(
            "firstName", oldValue, newValue);
    }

    public java.lang.String getFirstName() {
        return this.firstName;
    }

    public void setId(java.lang.Integer newValue) {
        java.lang.Integer oldValue = this.id;
        this.id = newValue;
        this.changes.firePropertyChange("id", oldValue, newValue);
    }

    public java.lang.Integer getId() {
        return this.id;
    }

    public void setLastName(java.lang.String newValue) {
        java.lang.String oldValue = this.lastName;
        this.lastName = newValue;
        this.changes.firePropertyChange(
            "lastName", oldValue, newValue);
    }

    public java.lang.String getLastName() {
        return this.lastName;
    }

    public PropertyChangeListener[] allPropertyChangeListeners() {
        return changes.getPropertyChangeListeners();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void addPropertyChangeListener(
        String propertyName, PropertyChangeListener l) {
        changes.addPropertyChangeListener(propertyName, l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public void removePropertyChangeListener(
        String propertyName, PropertyChangeListener l) {
        changes.removePropertyChangeListener(propertyName, l);
    }
}
