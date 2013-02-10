package com.manning.gwtip.bookstore.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Review implements IsSerializable {
    private java.lang.String author;
    private java.lang.Integer id;
    private java.lang.String text;
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(
            this);
    private int rating;

    public void setAuthor(java.lang.String newValue) {
        java.lang.String oldValue = this.author;
        this.author = newValue;
        this.changes.firePropertyChange(
            "author", oldValue, newValue);
    }

    public java.lang.String getAuthor() {
        return this.author;
    }

    public void setId(java.lang.Integer newValue) {
        java.lang.Integer oldValue = this.id;
        this.id = newValue;
        this.changes.firePropertyChange("id", oldValue, newValue);
    }

    public java.lang.Integer getId() {
        return this.id;
    }

    public PropertyChangeListener[] allPropertyChangeListeners() {
        return changes.getPropertyChangeListeners();
    }

    public void setRating(int newValue) {
        int oldValue = this.rating;
        this.rating = newValue;
        this.changes.firePropertyChange(
            "rating", oldValue, newValue);
    }

    public int getRating() {
        return this.rating;
    }

    public void setText(java.lang.String newValue) {
        java.lang.String oldValue = this.text;
        this.text = newValue;
        this.changes.firePropertyChange(
            "text", oldValue, newValue);
    }

    public java.lang.String getText() {
        return this.text;
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
