package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import com.manning.gwtip.bookstore.client.ModelState;
import com.manning.gwtip.bookstore.client.model.Book;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.List;


public class BookList extends FlowPanel {
    HorizontalPanel content = null;
    ModelState model = ModelState.getInstance();
    PropertyChangeListener listener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                update();
            }
        };

    public BookList() {
        super();
        this.update();
        this.setWidth("70%");
        this.update();
    }

    protected void onAttach() {
        super.onAttach();
        model.addPropertyChangeListener("books", listener);
    }

    protected void onDetach() {
        super.onDetach();
        model.removeProertyChangeListener(listener);
    }

    private void update() {
        if(content != null) {
            this.remove(content);
        }

        ScrollPanel p = new ScrollPanel();
        HorizontalPanel v = new HorizontalPanel();
        List books = model.getBooks();

        for(int i = 0; (books != null) && (i < books.size()); i++) {
            v.add(new BookSummary((Book) books.get(i)));
        }

        p.setWidget(v);
        this.add(p);
    }
}
