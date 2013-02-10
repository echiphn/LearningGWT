
package com.manning.gwtip.javascript.scriptaculous.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class DragAndDrop {
    
    public static final String CONSTRAINT_VERTICAL="vertical";
    public static final String CONSTRAINT_HORIZONTAL="horizontal";
    public static final String OVERLAP_VERTICAL="vertical";
    public static final String OVERLAP_HORIZONTAL="horizontal";
    
    
    private static HashSet droppables = new HashSet();
    private static HashMap dropListeners = new HashMap();
    private static HashMap draggables = new HashMap();
    private static HashMap dragListeners = new HashMap();
    private static HashMap elementsToUIObjects = new HashMap();
    private static ArrayList dragObservers = new ArrayList();
    static{
        initDragObservers();
    }
    private DragAndDrop() {
    }
    
    public static void setDroppable( UIObject target,
            String[] acceptClasses,
            UIObject[] containment,
            String hoverClass,
            String overlap,
            boolean greedy
            ){
        JavaScriptObject acceptNative = javaArrayToJavaScriptArray( acceptClasses );
        JavaScriptObject containmentNative = null;
        if( containment != null ){
            Element[] containElements = new Element[containment.length];
            for( int i=0; i< containment.length; i++){
                containElements[i] = containment[i].getElement();
            }
            containmentNative = javaArrayToJavaScriptArray(containElements);
        }
        droppables.add( target );
        setDroppable( target.getElement(), 
                acceptNative,
                containmentNative,
                hoverClass,
                overlap,
                greedy, 
                target );
        
    }
    
    private static JavaScriptObject javaArrayToJavaScriptArray(Object[] array){
        JavaScriptObject jsArray = null;
        for( int i=0; array != null && i < array.length; i++){
            if( array[i] instanceof String ){
                jsArray = addToArray( jsArray, (String) array[i]);
            } else {
                jsArray = addToArray( jsArray, (JavaScriptObject) array[i]);
            }
        }
        return jsArray;
    }
    
    private static native JavaScriptObject addToArray( 
            JavaScriptObject array, JavaScriptObject object)/*-{
        if( array == null ) array = new Array();                                                     
        array.push( object );
        return array;
    }-*/;
    private static native JavaScriptObject addToArray( 
            JavaScriptObject array, String object)/*-{
        if( array == null ) array = new Array();                                                     
        array.push( object );
        return array;
    }-*/;
    
    
    private static native void setDroppable( Element target, 
            JavaScriptObject acceptClasses,
            JavaScriptObject containment, 
            String hoverClass,
            String overlap,
            boolean greedy,
            UIObject uiObject )/*-{
        $wnd.Droppables.add( target,
            {
               accept: acceptClasses,
               containment: containment,
               hoverclass: hoverClass,
               overlap: overlap,
               greedy: greedy,
               onDrop: function(draggable, droppable, overlap){
                            @com.manning.gwtip.javascript.scriptaculous.client.DragAndDrop::fireOnDrop(Lcom/google/gwt/user/client/ui/UIObject;Lcom/google/gwt/user/client/Element;)(uiObject, draggable );
                       },
               onHover: function(draggable, droppable){
                            @com.manning.gwtip.javascript.scriptaculous.client.DragAndDrop::fireOnHover(Lcom/google/gwt/user/client/ui/UIObject;Lcom/google/gwt/user/client/Element;)(uiObject, draggable );
                       },  
            });
    }-*/;
    
    public static void unsetDroppable(UIObject target){
        if( droppables.contains( target) ){
            unsetDroppable( target.getElement());
            dropListeners.remove( target );
            if( draggables.get( target ) != null ){
                elementsToUIObjects.remove( target );
            }
        }
    } 
    
    private static native void unsetDroppable(Element element )
    /*-{ $wnd.Droppables.remove( element ); }-*/;
    
    
    private static void fireOnHover(UIObject source, Element hovered ){
        UIObject hoveredUIObject = (UIObject) elementsToUIObjects.get( hovered );
        ArrayList listeners = (ArrayList) dropListeners.get( source );
        for( int i=0; listeners != null && i < listeners.size(); i++ ){
            ((DropListener) listeners.get(i) ).onHover(source, hoveredUIObject );
        }       
    }
    
    private static void fireOnDrop( UIObject source, Element dropped ){
        UIObject droppedUIObject = (UIObject) elementsToUIObjects.get( dropped );
        ArrayList listeners = (ArrayList) dropListeners.get( source );
        for( int i=0; listeners != null && i < listeners.size(); i++ ){
            ((DropListener) listeners.get(i) ).onDrop(source, droppedUIObject );
        } 
        
    }
    public static void addDropListener( UIObject target, DropListener listener){
        ArrayList listeners = (ArrayList) dropListeners.get( target );
        if( listeners == null ){
            listeners = new ArrayList();
            dropListeners.put( target, listeners );
        }
        listeners.add( listener );
    }
    
    public static void setDraggable( UIObject source,
            UIObject handle,
            boolean revert,
            int zIndex,
            String constraint,
            boolean ghosting
            ){
        if( draggables.get( source ) != null ){
            unsetDraggable( source );
        }
        JavaScriptObject draggable = setDraggable( source.getElement() ,
                (handle == null ? null : handle.getElement()),
                revert,
                zIndex,
                constraint,
                ghosting);
        draggables.put( source, draggable );
        elementsToUIObjects.put( source.getElement(), source );
    }
    
    private static native JavaScriptObject setDraggable(
            JavaScriptObject element,
            JavaScriptObject handle,
            boolean revert,
            int zIndex,
            String constraint,
            boolean ghosting
            )/*-{
        return new $wnd.Draggable(
                element,
                {
                handle : handle,
                revert : revert,
                zindex : zIndex,
                constraint: constraint,
                ghosting: ghosting
                });
    }-*/;
    
    
    public static void unsetDraggable( UIObject source ){
        if( draggables.get( source ) != null ){
            JavaScriptObject draggable = (JavaScriptObject) draggables.remove(source);
            if( !droppables.contains( source ) )
                elementsToUIObjects.remove( source.getElement() );
            destroyDragable( draggable );
        }
    }
    
    private static native void destroyDragable(JavaScriptObject draggable)
    /*-{ draggable.destroy(); }-*/;
    
    public static void addDragListener( UIObject source, DragListener listener ){
        JavaScriptObject draggable = (JavaScriptObject) draggables.get( source );
        if( draggable == null ){
            throw new RuntimeException( "That is not a draggable object.");
        }
        ArrayList listeners = (ArrayList) dragListeners.get( source );
        if( listeners == null ){
            listeners = new ArrayList();
            dragListeners.put( source, listeners );
        }
        listeners.add( listener );
        unsetChange( draggable );
        for( Iterator it = listeners.iterator(); it.hasNext(); ){
            appendListener( source, draggable, (DragListener) it.next() );
        }
    }
    
    private static native void unsetChange( JavaScriptObject draggable )/*-{
        draggable.options.change = null;
    }-*/;
    
    private static native void appendListener(
            UIObject source, JavaScriptObject draggable, DragListener listener )/*-{
        var oldChange = draggable.options.change;
        var newChange = function(element){
            if( oldChange ) oldChange(element);
            listener.@com.manning.gwtip.javascript.scriptaculous.client.DragListener::onChange(Lcom/google/gwt/user/client/ui/UIObject;)( source );
        }
        draggable.options.change = newChange;
    }-*/;
    
    public static void remoteDragListener(UIObject source, DragListener listener ){
        JavaScriptObject draggable = (JavaScriptObject) draggables.get( source );
        if( draggable == null ){
            throw new RuntimeException( "That is not a draggable object.");
        }
        ArrayList listeners = (ArrayList) dragListeners.get( source );
        if( listeners != null ){
            listeners.remove( listener );
            unsetChange( draggable );
            for( Iterator it = listeners.iterator(); it.hasNext(); ){
                appendListener( source, draggable, (DragListener) it.next() );
            }
        }
    }
    
    private static void fireOnStart(String name, Element element ){
        for(Iterator it = dragObservers.iterator(); it.hasNext(); ){
            ((DragObserver) it.next())
                    .onStart( 
                        name, 
                        (UIObject) elementsToUIObjects.get(element)
                    );
        }
    }
    private static void fireOnDrag(String name, Element element ){
        for(Iterator it = dragObservers.iterator(); it.hasNext(); ){
            ((DragObserver) it.next())
                    .onDrag( 
                        name, 
                        (UIObject) elementsToUIObjects.get(element)
                    );
        }
    }
    private static void fireOnEnd(String name, Element element ){
        for(Iterator it = dragObservers.iterator(); it.hasNext(); ){
            ((DragObserver) it.next())
                    .onEnd( 
                        name, 
                        (UIObject) elementsToUIObjects.get(element)
                    );
        }
    }
    
    public static void addDragObserver( DragObserver observer ){
        dragObservers.add( observer );
    }
    public static void removeDragObserver( DragObserver observer ){
        dragObservers.remove( observer );
    }
    
    private static native void initDragObservers()/*-{
        var observer = {
                onStart: function( name, draggable, event ){
                    @com.manning.gwtip.javascript.scriptaculous.client.DragAndDrop::fireOnStart(Ljava/lang/String;Lcom/google/gwt/user/client/Element;)(name, draggable.element);
                },
                onEnd: function( name, draggable, event ){
                    @com.manning.gwtip.javascript.scriptaculous.client.DragAndDrop::fireOnEnd(Ljava/lang/String;Lcom/google/gwt/user/client/Element;)(name, draggable.element);
                },
                onDrag: function( name, draggable, event ){
                    @com.manning.gwtip.javascript.scriptaculous.client.DragAndDrop::fireOnDrag(Ljava/lang/String;Lcom/google/gwt/user/client/Element;)(name, draggable.element);
                }
        };
              
        $wnd.Draggables.addObserver(observer);
    }-*/;
}
