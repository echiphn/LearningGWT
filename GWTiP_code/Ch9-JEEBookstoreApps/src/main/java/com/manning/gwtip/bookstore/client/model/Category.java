package com.manning.gwtip.bookstore.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Category implements IsSerializable {
    
    private java.lang.String name;
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(
            this);

    
    public void setName(java.lang.String newValue) {
        java.lang.String oldValue = this.name;
        this.name = newValue;
        this.changes.firePropertyChange(
            "name", oldValue, newValue);
    }

    public java.lang.String getName() {
        return this.name;
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
