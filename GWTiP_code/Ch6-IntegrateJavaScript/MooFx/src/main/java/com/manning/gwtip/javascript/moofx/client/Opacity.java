
package com.manning.gwtip.javascript.moofx.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.UIObject;


public class Opacity extends Effect {
    
    public Opacity( UIObject uiObject, EffectListener handler,
            long duration , JavaScriptObject transition) {
        super( uiObject, handler, duration, transition );   
        this.nativeEffect = getEffectClass( uiObject.getElement(), 
                duration,
                handler,
            transition);
    }
    
    private native JavaScriptObject getEffectClass(
            JavaScriptObject element, 
            long time,
            EffectListener handler,
            JavaScriptObject transitionFunction )/*-{
        var complete = function(){
            if( handler != null ){
                handler.@com.manning.gwtip.javascript.moofx.client.EffectListener::onComplete()();
            }
        };                                         
        return new $wnd.fx.Opacity( element, { duration: time, transition: transitionFunction, onComplete: complete } ); 
    }-*/;
    
    
}
