
package com.manning.gwtip.scriptaculous.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.manning.gwtip.javascript.scriptaculous.client.DragAndDrop;
import com.manning.gwtip.javascript.scriptaculous.client.DragObserver;
import com.manning.gwtip.javascript.scriptaculous.client.DropListener;

public class MyEntryPoint implements EntryPoint {
    //Image img = new Image( GWT.getModuleBaseURL()+"/fireworks.jpg");
    VerticalPanel panel = new VerticalPanel();
    HorizontalPanel hpanel = new HorizontalPanel();
    VerticalPanel output = new VerticalPanel();
    public MyEntryPoint() {
        super();
    }
    
   
    
    public void onModuleLoad() {
        Image trash = new Image("trash.png");
        String[] accept = {"file"};
        DragAndDrop.setDroppable( trash, accept, null, null, null, true );
        DragAndDrop.addDropListener( trash, new DropListener(){
            public void onHover(UIObject source, UIObject hovered) {
                output.add( new Label( "Hover "+((File) hovered).name ));
            }

            public void onDrop(UIObject source, UIObject dropped) {
                output.add( new Label( "Drop file "+ ((File) dropped).name ));
                //DragAndDrop.unsetDraggable( dropped );
                //dropped.setVisible( false );
            }
            
        });
        
        hpanel.add( panel );
        hpanel.add( trash );
        File f = new File( "foo", "webpage.png");
        f.setStyleName("file");
        panel.add( f );
        DragAndDrop.setDraggable( f, null, false, 1000, null, true);
        
        f = new File( "bar", "webpage.png");
        f.setStyleName("file");
        panel.add( f );
        DragAndDrop.setDraggable( f, null, false, 1000, null, true);
        
        f = new File( "baz", "webpage.png");
        f.setStyleName("file");
        panel.add( f );
        DragAndDrop.setDraggable( f, null, false, 1000, null, true);
        
        
        DragAndDrop.addDragObserver( new DragObserver(){
            public void onStart(String eventName, UIObject source) {
                output.add( new Label(eventName +" "+ ((Image) source).getUrl() ));
            }

            public void onEnd(String eventName, UIObject source) {
                output.add( new Label(eventName +" "+ ((Image) source).getUrl() ));
            }

            public void onDrag(String eventName, UIObject source) {
                output.add( new Label(eventName +" "+ ((Image) source).getUrl() ));
            }
            
        });
        
        
        RootPanel.get().add( hpanel );
        RootPanel.get().add( output );
        
        
        
        /*DragAndDrop.addDragObserver( new DragObserver(){
            public void onStart(String eventName, UIObject source) {
                panel.add( new Label(eventName +" "+ ((Image) source).getUrl() ));
            }

            public void onEnd(String eventName, UIObject source) {
                panel.add( new Label(eventName +" "+ ((Image) source).getUrl() ));
            }

            public void onDrag(String eventName, UIObject source) {
                panel.add( new Label(eventName +" "+ ((Image) source).getUrl() ));
            }
            
        });
        
        Button b = new Button( "Make Draggable", new ClickListener(){
            
            public void onClick(Widget widget) {
                DragAndDrop.setDraggable( img, null, true, 1000, null, false);
                DragAndDrop.addDragListener( img, new DragListener(){
                    public void onChange(UIObject source){
                        panel.add( new Label("Listener 1 event."));
                    }
                });
                DragAndDrop.addDragListener( img, new DragListener(){
                    public void onChange(UIObject source){
                        panel.add( new Label("Listener 2 event."));
                    }
                });
                       
            }
        });
        panel.add( b );
        
        
        panel.add( img );
        RootPanel.get().add( panel );
         */
        
    }
}
