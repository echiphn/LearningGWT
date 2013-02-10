package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.user.client.ui.ListBox;

import com.manning.gwtip.bookstore.client.ModelState;
import com.manning.gwtip.bookstore.client.model.Category;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CategorySelect extends ListBox {
    private List categories;
    private PropertyChangeListener l = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                categories = (List) propertyChangeEvent.getNewValue();

                if(categories == null) {
                    categories = new ArrayList();
                }

                update();
            }
        };

    public CategorySelect() {
        ModelState model = ModelState.getInstance();
        model.addPropertyChangeListener("categories", l);
        this.categories = model.getCategories();
        this.update();
        this.setMultipleSelect(true);
        this.setVisibleItemCount(5);
        this.setStyleName("categorySelect");
    }

    public void cleanup() {
        ModelState.getInstance().removePropertyChangeListener("categories", l);
    }

    public List getSelectedCategories() {
        ArrayList values = new ArrayList();

        for(int i = 0; i < this.getItemCount(); i++) {
            if(this.isItemSelected(i)) {
                values.add(categories.get(i));
            }
        }

        return values;
    }

    public void setSelectedCategories(List categories) {
        for(int i = 0; i < this.getItemCount(); i++) {
            boolean selected = false;

            for(int j = 0; (categories != null) && (j < categories.size());
                    j++) {
                Category c = (Category) categories.get(j);

                if(this.getItemText(i).equals(c.getName())) {
                    selected = true;

                    break;
                }
            }

            this.setItemSelected(i, selected);
        }
    }

    private void update() {
        this.clear();

        if(categories == null) {
            return;
        }

        for(Iterator it = categories.iterator(); it.hasNext();) {
            Category c = (Category) it.next();
            this.addItem(c.getName());
        }
    }
}
