package com.manning.gwtip.bookstore.client.model;


import com.google.gwt.user.client.rpc.IsSerializable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Book implements IsSerializable {
    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Author>
     */
    private java.util.List authors;

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Category>
     */
    private java.util.List categories;
    private java.lang.String description = "";
    private java.lang.Integer id;
    private String image;

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Review>
     */
    private java.util.List reviews;
    private java.lang.String title ="";
    private transient PropertyChangeSupport changes = new PropertyChangeSupport(
            this);

    /**
     * @gwt.typeArgs newValue <com.manning.gwtip.bookstore.client.model.Author>
     */
    public void setAuthors(java.util.List newValue) {
        java.util.List oldValue = this.authors;
        this.authors = newValue;
        this.changes.firePropertyChange(
            "authors", oldValue, newValue);
    }

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Author>
     */
    public java.util.List getAuthors() {
        return this.authors;
    }

    /**
     * @gwt.typeArgs newValue <com.manning.gwtip.bookstore.client.model.Category>
     */
    public void setCategories(java.util.List newValue) {
        java.util.List oldValue = this.categories;
        this.categories = newValue;
        this.changes.firePropertyChange(
            "categories", oldValue, newValue);
    }

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Category>
     */
    public java.util.List getCategories() {
        return this.categories;
    }

    public void setDescription(java.lang.String newValue) {
        java.lang.String oldValue = this.description;
        this.description = newValue;
        this.changes.firePropertyChange(
            "description", oldValue, newValue);
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setId(java.lang.Integer newValue) {
        java.lang.Integer oldValue = this.id;
        this.id = newValue;
        this.changes.firePropertyChange("id", oldValue, newValue);
    }

    public java.lang.Integer getId() {
        return this.id;
    }

    public void setImage(String newValue) {
        String oldValue = this.image;
        this.image = newValue;
        this.changes.firePropertyChange(
            "image", oldValue, newValue);
    }

    public String getImage() {       
        return this.image;
    }

    public PropertyChangeListener[] allPropertyChangeListeners() {
        return changes.getPropertyChangeListeners();
    }

    /**
     * @gwt.typeArgs newValue <com.manning.gwtip.bookstore.client.model.Review>
     */
    public void setReviews(java.util.List newValue) {
        java.util.List oldValue = this.reviews;
        this.reviews = newValue;
        this.changes.firePropertyChange(
            "reviews", oldValue, newValue);
    }

    /**
     * @gwt.typeArgs <com.manning.gwtip.bookstore.client.model.Review>
     */
    public java.util.List getReviews() {
        return this.reviews;
    }

    public void setTitle(java.lang.String newValue) {
        java.lang.String oldValue = this.title;
        this.title = newValue;
        this.changes.firePropertyChange(
            "title", oldValue, newValue);
    }

    public java.lang.String getTitle() {
        return this.title;
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
