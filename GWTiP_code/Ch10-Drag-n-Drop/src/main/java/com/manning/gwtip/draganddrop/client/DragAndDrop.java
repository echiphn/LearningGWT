

package com.manning.gwtip.draganddrop.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;

public class DragAndDrop extends FocusPanel{
    private boolean mouseInPanel = false;
    private SourcesMouseEvents currentDraggable;
    private int offsetX;
    private int offsetY;
    private ArrayList dragListeners = null;
    private final MouseListener basicDragListener = new MouseListener(){
            public void onMouseLeave(Widget widget) {
            }

            public void onMouseEnter(Widget widget) {
            }

            public void onMouseUp(Widget widget, int x, int y) {
                currentDraggable = null;
                GWT.log( "Stop drag", null);
                restoreOpacity( widget.getElement() );
            }

            public void onMouseMove(Widget widget, int x, int y) {
            }

            public void onMouseDown(Widget widget, int x, int y) {
                currentDraggable = (SourcesMouseEvents) widget;
                offsetX = x;
                offsetY = y;
                fireOnStart( currentDraggable );
                halfOpacity( widget.getElement() );
                GWT.log( "Start Drag. "+x+","+y, null);
            }
            
        }; 
    /** Creates a new instance of DragAndDrop */
    public DragAndDrop() {
        this.addMouseListener( new MouseListener(){
            public void onMouseLeave(Widget widget) {
                mouseInPanel = false;
            }

            public void onMouseEnter(Widget widget) {
                mouseInPanel = true;
            }

            public void onMouseUp(Widget widget, int x, int y) {
            }

            public void onMouseMove(Widget widget, int x, int y) {
                if( currentDraggable != null  && mouseInPanel){
                    GWT.log( "Drag", null);
                    UIObject o = (UIObject) currentDraggable;
                    move( o.getElement(), y - offsetY , x - offsetX );
                }
            }

            public void onMouseDown(Widget widget, int x, int y) {
            }
            
        });
    }
    
    public void makeDraggable( SourcesMouseEvents source ){
        source.addMouseListener( basicDragListener );
    }
    
    private void fireOnStart( SourcesMouseEvents source ){
        for( int i =0; dragListeners != null && i < dragListeners.size(); i++){
            ((DragListener) dragListeners.get(i)).onStart( source );
        }
    }
    private void fireOnEnd( SourcesMouseEvents source ){
        for( int i =0; dragListeners != null && i < dragListeners.size(); i++){
            ((DragListener) dragListeners.get(i)).onEnd( source );
        }
    }
    private void fireOnDrag( SourcesMouseEvents source ){
        for( int i =0; dragListeners != null && i < dragListeners.size(); i++){
            ((DragListener) dragListeners.get(i)).onDrag( source );
        }
    }
    
    private static native void move( Element e, int top, int left )/*-{
        e.style.position = "absolute";                                                    
        e.style.top = top;
        e.style.left = left;                                                                                                                                               
    }-*/;
    
    private static native void halfOpacity( Element e )/*-{
        if( !e.originalOpacity )                                                            
             e.originalOpacity = (e.style.opacity)  ? e.style.opacity : 1;
        e.style.opacity = e.originalOpacity * .5;
        e.style.filter = "alpha(opacity="+ (e.orignalOpacity * 50) + ")"; 
    }-*/;
    
    private static native void restoreOpacity( Element e) /*-{
        e.style.opacity = (e.originalOpacity) ? e.originalOpacity : 1;  
        e.style.filter = "alpha(opacity="+ (e.orignalOpacity * 100) +")";                                                           
        e.originalOpacity = null;
    }-*/;
    private static native void makeRelative( Element e )/*-{
        e.style.position = "relative";
        e.style.top = null; e.style.left = null;        
                                                                                                                                                  
    }-*/;                  
    
}
