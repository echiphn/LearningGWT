package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.manning.gwtip.bookstore.client.BookstoreConstants;
import com.manning.gwtip.bookstore.client.model.Author;


public class AuthorEdit extends VerticalPanel {
    private static final BookstoreConstants CONSTANTS = (BookstoreConstants) GWT.create(BookstoreConstants.class);
    private Author author;
    private TextArea bio = new TextArea();
    private TextBox firstName = new TextBox();
    private TextBox lastName = new TextBox();

    /** Creates a new instance of AuthorEdit */
    public AuthorEdit(final Author author) {
        super();
        this.author = author;
        this.add(new Label(CONSTANTS.firstName()));
        this.add(firstName);
        this.add(new Label(CONSTANTS.lastName()));
        this.add(lastName);
        this.add(new Label(CONSTANTS.description()));
        this.add(bio);
        firstName.addChangeListener(new ChangeListener() {
                public void onChange(Widget widget) {
                    author.setFirstName(firstName.getText());
                }
            });
        lastName.addChangeListener(new ChangeListener() {
                public void onChange(Widget widget) {
                    author.setLastName(lastName.getText());
                }
            });
        bio.addChangeListener(new ChangeListener() {
                public void onChange(Widget widget) {
                    author.setBio(bio.getText());
                }
            });
    }

    public Author getAuthor() {
        return this.author;
    }
}
