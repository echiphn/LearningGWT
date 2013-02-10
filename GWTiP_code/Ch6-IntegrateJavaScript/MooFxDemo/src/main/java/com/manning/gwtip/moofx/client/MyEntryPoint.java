
package com.manning.gwtip.moofx.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtip.javascript.moofx.client.EffectListener;
import com.manning.gwtip.javascript.moofx.client.Height;
import com.manning.gwtip.javascript.moofx.client.Opacity;
import com.manning.gwtip.javascript.moofx.client.Transition;
import com.manning.gwtip.javascript.moofx.client.Width;

public class MyEntryPoint implements EntryPoint {
    Image img = new Image( GWT.getModuleBaseURL()+"/fireworks.jpg");
    
    Height height = null;
    Width width = null;
    Opacity opacity = null;
    EffectListener listener =  new EffectListener(){
        public void onComplete() {
            Window.alert("complete");
        }
        
    };
    public MyEntryPoint() {
        super();
    }
    
    public void onModuleLoad() {
        
        
        Button b = new Button( "Toggle Height", new ClickListener(){
            
            public void onClick(Widget widget) {
                height = height == null ?  new Height( img,
                        listener,
                        500,
                        Transition.SINOIDAL
                        ) : height;
                height.toggle();
                
            }
        });
        RootPanel.get().add( b );
        
        b = new Button( "Toggle Width", new ClickListener(){
            
            public void onClick(Widget widget) {
                width = width == null ?  new Width( img,
                        listener,
                        500,
                        Transition.LINEAR
                        ) : width;
                width.toggle();
                
            }
        });
        RootPanel.get().add( b );
        b = new Button( "Toggle Opacity", new ClickListener(){
            
            public void onClick(Widget widget) {
                opacity = opacity == null ?  new Opacity( img,
                        listener,
                        5000,
                        Transition.CIRC
                        ) : opacity;
                opacity.toggle();
                
            }
        });
        RootPanel.get().add( b );
        
        RootPanel.get().add( img );
    }
}
