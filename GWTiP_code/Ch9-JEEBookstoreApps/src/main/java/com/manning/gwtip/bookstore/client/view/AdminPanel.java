package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.manning.gwtip.bookstore.client.BookstoreConstants;
import com.manning.gwtip.bookstore.client.Controller;
import com.manning.gwtip.bookstore.client.ModelState;
import com.manning.gwtip.bookstore.client.model.Book;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Iterator;
import java.util.List;


public class AdminPanel extends HorizontalPanel {
    private static final BookstoreConstants CONSTANTS = (BookstoreConstants) GWT.create(BookstoreConstants.class);
    private Book book;
    private BookEdit edit;
    private Button deleteBook = new Button(CONSTANTS.delete());
    private Button newBook = new Button(CONSTANTS.create());
    private HorizontalPanel buttons = new HorizontalPanel();
    private ListBox select = new ListBox();
    private ModelState model = ModelState.getInstance();
    private PropertyChangeListener bookListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                List books = (List) propertyChangeEvent.getNewValue();
                select.clear();

                for(Iterator it = books.iterator(); it.hasNext();) {
                    Book b = (Book) it.next();
                    select.addItem(b.getTitle(), b.getId().toString());
                }
            }
        };

    private VerticalPanel side = new VerticalPanel();

    public AdminPanel() {
        this.setStyleName("adminPanel");
        side.add(new Label(CONSTANTS.books()));
        buttons.add(newBook);
        buttons.add(deleteBook);
        side.add(buttons);
        newBook.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    if(edit != null) {
                        remove(edit);
                        edit.cleanup();
                    }

                    book = new Book();
                    edit = new BookEdit(book);
                    add(edit);
                }
            });
        deleteBook.addClickListener(new ClickListener() {
                public void onClick(Widget widget) {
                    Controller.getInstance().deleteBook(book);

                    if(edit != null) {
                        remove(edit);
                        edit.cleanup();
                    }
                }
            });
        model.addPropertyChangeListener("books", bookListener);
        select.addChangeListener(new ChangeListener() {
                public void onChange(Widget widget) {
                    if(edit != null) {
                        remove(edit);
                        edit.cleanup();
                    }

                    if(select.getSelectedIndex() == -1) {
                        return;
                    }

                    book = (Book) model.getBooks().get(select.getSelectedIndex());
                    edit = new BookEdit(book);
                    add(edit);
                }
            });
        select.setVisibleItemCount(10);
        side.add(select);
        Controller.getInstance().findAllBooks();
        Controller.getInstance().findAllAuthors();
        this.add(side);
    }
}
