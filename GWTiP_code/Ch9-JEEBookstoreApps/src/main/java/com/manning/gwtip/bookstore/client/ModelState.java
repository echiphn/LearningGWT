package com.manning.gwtip.bookstore.client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModelState {
    
    private PropertyChangeSupport changes = new PropertyChangeSupport( this );
    
    private List books;
    
    private List authors;
    
    private List categories;
    
    private static ModelState instance;
    
    
    /** Creates a new instance of ModelState */
    private ModelState() {
        super();
    }
    
    public static ModelState getInstance(){
        return instance == null ? instance = new ModelState() : instance ;
    }
    
    
    public void addPropertyChangeListener( PropertyChangeListener l ){
        changes.addPropertyChangeListener( l );
    }
    public void addPropertyChangeListener( String property, PropertyChangeListener l ){
        changes.addPropertyChangeListener( property, l );
    }
    public void removeProertyChangeListener( PropertyChangeListener l ){
        changes.removePropertyChangeListener( l );
    }
    public void removePropertyChangeListener( String property, PropertyChangeListener l ){
        changes.removePropertyChangeListener( property, l);
    }
    
    public void clearPropertyChangeListeners(){
        PropertyChangeListener[] listeners = changes.getPropertyChangeListeners();
        for( int i=0; listeners != null && i < listeners.length; i++ ){
            this.removeProertyChangeListener( listeners[i] );
        }
    }

    public List getCategories() {
        return categories;
    }

    public void setCategories(List categories) {
        this.categories = categories == null ? new ArrayList() : categories;
        changes.firePropertyChange("categories", null, this.categories);
    }
    
    public void setBooks( List books ){
        this.books = books == null ? new ArrayList() : books;
        changes.firePropertyChange( "books", null, this.books);
    }

    public List getAuthors() {
        return authors;
    }
    
    public List getBooks(){
        return books;
    }

    public void setAuthors(List authors) {
        this.authors = authors == null ? new ArrayList() : authors;
        changes.firePropertyChange( "authors", null, this.authors);
    }
    
}
