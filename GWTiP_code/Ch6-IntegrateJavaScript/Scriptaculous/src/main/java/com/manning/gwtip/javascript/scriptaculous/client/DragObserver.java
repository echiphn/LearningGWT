package com.manning.gwtip.javascript.scriptaculous.client;

import com.google.gwt.user.client.ui.UIObject;

public interface DragObserver {
    
    public void onStart( String eventName, UIObject source );
    
    public void onDrag( String eventName, UIObject source );
    
    public void onEnd(String eventName, UIObject source );
    
}
