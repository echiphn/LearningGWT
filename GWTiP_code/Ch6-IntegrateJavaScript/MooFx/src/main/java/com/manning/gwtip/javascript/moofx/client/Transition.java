package com.manning.gwtip.javascript.moofx.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

public class Transition {
    public static final JavaScriptObject SINOIDAL = getSinoidal();
    public static final JavaScriptObject LINEAR = getLinear();
    public static final JavaScriptObject CUBIC = getCubic();
    public static final JavaScriptObject CIRC = getCirc();
    
    private static native JavaScriptObject getSinoidal()
    /*-{return $wnd.fx.sinoidal; }-*/;
    private static native JavaScriptObject getLinear()
    /*-{ return  $wnd.fx.linear; }-*/;
    private static native JavaScriptObject getCubic()
    /*-{  return  $wnd.fx.cubic; }-*/;
    private static native JavaScriptObject getCirc()
    /*-{  return  $wnd.fx.circ; }-*/;
    
}
