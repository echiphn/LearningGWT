package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.manning.gwtip.bookstore.client.BookstoreConstants;
import com.manning.gwtip.bookstore.client.Controller;
import com.manning.gwtip.bookstore.client.ModelState;
import com.manning.gwtip.bookstore.client.model.Author;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AuthorSearch extends Composite {
    private BookstoreConstants constants = (BookstoreConstants) GWT.create(BookstoreConstants.class);
    private Button search = new Button(constants.search());
    private Label authLabel = new Label(constants.author());
    private Label fnLabel = new Label(constants.firstName());
    private Label lnLabel = new Label(constants.lastName());
    private List authors;
    private ListBox list = new ListBox();
    private ModelState model = ModelState.getInstance();
    private PropertyChangeListener listener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                authors = (List) propertyChangeEvent.getNewValue();
                update();
            }
        };

    private TextBox firstName = new TextBox();
    private TextBox lastName = new TextBox();
    private VerticalPanel main = new VerticalPanel();

    /** Creates a new instance of AuthorSearch */
    public AuthorSearch() {
        authors = model.getAuthors();
        authLabel.setStyleName("bookstore-heading");
        main.add(authLabel);
        main.add(fnLabel);
        main.add(firstName);
        main.add(lnLabel);
        main.add(lastName);
        main.add(search);
        list.setVisibleItemCount(5);
        main.add(list);
        model.addPropertyChangeListener("authors", listener);

        search.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    Controller.getInstance()
                              .updateAuthors(firstName.getText(),
                        lastName.getText());
                }
            });
    }

    public void addChangeListener(ChangeListener listener) {
        list.addChangeListener(listener);
    }

    public void cleanup() {
        model.removeProertyChangeListener(listener);
    }

    public List getSelectedAuthors() {
        ArrayList value = new ArrayList();

        for(int i = 0; i < authors.size(); i++) {
            if(list.isItemSelected(i)) {
                value.add(authors.get(i));
            }
        }

        return value;
    }

    public void removeChangeListener(ChangeListener listener) {
        list.removeChangeListener(listener);
    }

    private void update() {
        list.clear();

        for(Iterator it = authors.iterator(); it.hasNext();) {
            Author a = (Author) it.next();
            list.addItem(a.getLastName() + ", " + a.getFirstName());
        }
    }
}
