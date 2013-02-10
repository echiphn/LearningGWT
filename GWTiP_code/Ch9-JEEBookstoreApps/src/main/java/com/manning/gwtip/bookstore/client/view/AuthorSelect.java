package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.user.client.ui.ListBox;

import com.manning.gwtip.bookstore.client.ModelState;
import com.manning.gwtip.bookstore.client.model.Author;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AuthorSelect extends ListBox {
    private List authors;
    private PropertyChangeListener l = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                authors = (List) propertyChangeEvent.getNewValue();

                if(authors == null) {
                    authors = new ArrayList();
                }

                update();
            }
        };

    public AuthorSelect() {
        ModelState model = ModelState.getInstance();
        model.addPropertyChangeListener("authors", l);
        this.authors = model.getAuthors();
        this.update();
        this.setMultipleSelect(true);
        this.setVisibleItemCount(5);
        this.setStyleName("authorSelect");
    }

    public void cleanup() {
        ModelState.getInstance().removePropertyChangeListener("categories", l);
    }

    public List getSelectedAuthors() {
        ArrayList values = new ArrayList();

        for(int i = 0; i < this.getItemCount(); i++) {
            if(this.isItemSelected(i)) {
                values.add(authors.get(i));
            }
        }

        return values;
    }

    public void setSelectedAuthors(List authors) {
        for(int i = 0; i < this.getItemCount(); i++) {
            boolean selected = false;

            for(int j = 0; (authors != null) && (j < authors.size()); j++) {
                Author a = (Author) authors.get(j);
                if(this.getItemText(i)
                           .equals(a.getLastName() + ", " + a.getFirstName())) {
                    selected = true;
                    break;
                }
            }

            this.setItemSelected(i, selected);
        }
    }

    private void update() {
        this.clear();

        if(authors == null) {
            return;
        }

        for(Iterator it = authors.iterator(); it.hasNext();) {
            Author a = (Author) it.next();
            this.addItem(a.getLastName() + ", " + a.getFirstName());
        }
    }
}
