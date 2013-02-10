
package com.manning.gwtip.draganddrop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyEntryPoint implements EntryPoint {
    public MyEntryPoint() {
        super();
    }
    
    File f = new File( "trash", "trash.png");
    FocusPanel fPanel = new FocusPanel();
    
    DragAndDrop fp = new DragAndDrop();
    FlowPanel p = new FlowPanel();
    
    public void onModuleLoad() {
        VerticalPanel inner = new VerticalPanel();
        inner.add( new Label("Trash"));
        inner.add( f );
        fPanel.add( inner );
        
        p.add( fPanel );
        fp.add( p );
        fp.makeDraggable( fPanel );
        
        RootPanel.get().add( fp );
        
    }
    
                           
}
