package com.manning.gwtip.bookstore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.manning.gwtip.bookstore.client.view.AdminPanel;

public class BookstoreEntryPoint implements EntryPoint {
    
    public BookstoreEntryPoint() {
        super();
    }
    
    public void onModuleLoad() {
        
        Controller.getInstance().updateCategories();
        //TabPanel p = new TabPanel();
        //p.add( new AdminPanel(), "admin" );
        //p.add( new ShoppingDisplay(), "books" );
        RootPanel.get().add( new AdminPanel() );
        
    }
}
