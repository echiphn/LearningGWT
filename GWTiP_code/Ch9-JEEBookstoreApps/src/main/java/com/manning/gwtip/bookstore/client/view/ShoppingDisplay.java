package com.manning.gwtip.bookstore.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.manning.gwtip.bookstore.client.dnd.DragAndDrop;


/**
 *
 * @author cooper
 */
public class ShoppingDisplay extends Composite {
    private BookList list = new BookList();
    private HorizontalPanel main = new HorizontalPanel();
    private Cart cart = new Cart();
    

    /** Creates a new instance of ShoppingDisplay */
    public ShoppingDisplay() {
        super();
        main.add(list);
        DragAndDrop.getInstance().makeDroppable( cart );
        DragAndDrop.getInstance().addDropListener( cart, cart );
        main.add(cart);
        main.setWidth( "100%" );
        main.setHeight("100%");
        super.initWidget(main);
    }
}
