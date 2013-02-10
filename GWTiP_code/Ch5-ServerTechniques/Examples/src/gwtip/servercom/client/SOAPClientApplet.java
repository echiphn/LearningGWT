

package gwtip.servercom.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;


public class SOAPClientApplet {

    private static final String JAX_WS_JARS="activation.jar,FasInfoset.jar,http.jar,jaxb-api.jar,jaxb-impl.jar,jaxws-api.jar,jaxws-rt.jar,jsr173_api.jar,jsr181-api.jar,jsr250-api.jar,resolver.jar,saaj-api.jar,saaj-impl.jar,sjsxp.jar";
    private static SOAPClientApplet INSTANCE;
    private Element appletObject;
    
    public static SOAPClientApplet getInstance(){
        INSTANCE = INSTANCE == null ? new SOAPClientApplet() : INSTANCE;
        return INSTANCE;
    }
    
    private SOAPClientApplet() {
        
        this.appletObject = DOM.createElement( "applet");
        DOM.setAttribute( this.appletObject, "id", "SOAPClientApplet");
        DOM.setAttribute( this.appletObject, "MAYSCRIPT", "true" );
        DOM.setAttribute( this.appletObject, "code", "gwtip.servercom.applet.SOAPApplet");
        DOM.setAttribute( this.appletObject, "type","application/x-java-applet;version=1.5.0");
        DOM.setAttribute( this.appletObject, "pluginspage","http://java.sun.com/j2se/1.5.0/download.html");
        DOM.setAttribute( this.appletObject, "archive", JAX_WS_JARS+",GWTIP_Chapter5_Applet.jar");
        DOM.setAttribute( this.appletObject, "cache_archive", JAX_WS_JARS );
        DOM.setAttribute( this.appletObject, "width", "0px");
        DOM.setAttribute( this.appletObject, "height", "0px" );
        DOM.setAttribute( this.appletObject, "codebase", GWT.getModuleBaseURL()+"/applet/");
    }
    
    public static void intialize( ReadyCallback callback ){
        SOAPClientApplet.getInstance();
        INSTANCE.intializeJavaScript( callback );
    }
    
    private native Element intializeJavaScript(ReadyCallback callback)/*-{
        $wnd.appletSOAPClientReadyNotify = function(){
           callback.@gwtip.servercom.client.SOAPClientApplet.ReadyCallback::onReady()();
        }   
     }-*/;
    
    public static interface ReadyCallback{
        public void onReady();
    }
    
    public native String sayHello( JavaScriptObject person )/*-{
        $wnd.person = person;
        return $wnd.document.getElementById("SOAPClientApplet").sayHello();                                                   
    }-*/;
}
