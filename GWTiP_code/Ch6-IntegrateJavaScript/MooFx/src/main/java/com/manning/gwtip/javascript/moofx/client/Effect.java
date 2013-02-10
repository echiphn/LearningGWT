
package com.manning.gwtip.javascript.moofx.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.UIObject;

public class Effect {
    
    protected JavaScriptObject nativeEffect;
    protected EffectListener handler;
    protected UIObject uiObject;
    protected long duration;
    protected JavaScriptObject transition;

    protected Effect(UIObject uiObject, EffectListener handler,
            long duration , JavaScriptObject transition) {
        super();
        this.uiObject = uiObject;
        this.handler = handler;
        this.duration = duration;
        this.transition = transition;
       
    }
    
    
    
    public void toggle(){
        toggle( this.nativeEffect );
    }
    
    private native void toggle( JavaScriptObject effect )/*-{
        effect.toggle();
     }-*/;
    
    public void hide(){
        hide( this.nativeEffect );
    }
    
    private native void hide( JavaScriptObject effect )/*-{
        effect.hide();
     }-*/;
    
    public void clearTimer(){
        clearTimer( this.nativeEffect );
    }
    
    private native void clearTimer( JavaScriptObject effect )/*-{
        effect.clearTimer();
     }-*/;
    
    public void custom( int start, int end ){
        custom( this.nativeEffect, start, end );
    }
    
    private native void custom( JavaScriptObject nativeEffect, int start, int end )/*-{
        nativeEffect.custom( start, end );
    }-*/;
    
}
