package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import com.manning.gwtip.bookstore.client.BookstoreConstants;
import com.manning.gwtip.bookstore.client.dnd.DropListener;
import com.manning.gwtip.bookstore.client.model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Cart extends FlexTable implements DropListener {
    private static final BookstoreConstants CONSTANTS = (BookstoreConstants) GWT.create(BookstoreConstants.class);
    private List lineItems = new ArrayList();

    public Cart() {
        super();
        this.setStyleName("cart");
        this.setWidget(0, 1, new Label(CONSTANTS.title()));
        this.setWidget(0, 2, new Label(CONSTANTS.price()));
        this.setWidget(0, 3, new Label(CONSTANTS.quantity()));
        this.getRowFormatter().setVerticalAlign(0, HasVerticalAlignment.ALIGN_TOP);
        this.setWidth( "250px");
        this.setHeight( "400px");
        DOM.setStyleAttribute( this.getElement(), "border", "1px solid red");
    }

    public boolean onDrop(Widget dropped) {
        if(dropped instanceof CoverImage) {
            CoverImage cover = (CoverImage) dropped;
            boolean found = false;

            for(Iterator it = lineItems.iterator(); it.hasNext();) {
                LineItem item = (LineItem) it.next();

                if(item.book.getId().equals(cover.book.getId())) {
                    item.quantity++;
                    found = true;

                    break;
                }
            }

            if(!found) {
                LineItem item = new LineItem();
                item.book = cover.book;
                item.quantity = 1;
                lineItems.add(item);
            }

            update();
        }

        return true;
    }

    private void update() {
        for(int i = 0; i < (this.getRowCount() - 1); i++) {
            this.removeRow(1);
        }

        int position = 1;

        for(Iterator it = lineItems.iterator(); it.hasNext();) {
            LineItem item = (LineItem) it.next();
            this.setWidget(position, 1, new Label(item.book.getTitle()));
            this.setWidget(position, 2, new Label("$45"));
            this.setWidget(position, 3,
                new Label(Integer.toString(item.quantity)));
            this.getRowFormatter()
                .setVerticalAlign(position, HasVerticalAlignment.ALIGN_TOP);
        
            position++;
            
        }
    }

    private static class LineItem {
        Book book;
        int quantity;
    }
}
