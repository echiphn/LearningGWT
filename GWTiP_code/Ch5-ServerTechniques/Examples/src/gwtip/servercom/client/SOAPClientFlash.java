package gwtip.servercom.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;


public class SOAPClientFlash {
    private static final String MOVIE = GWT.getModuleBaseURL() +
            "/flash/SOAPClient.swf";
    private static SOAPClientFlash INSTANCE = null;
    private Element flashObject;
    private Element flashEmbed;
    private Element flashObjectReference;
    
    public static SOAPClientFlash getInstance(){
        INSTANCE = INSTANCE == null ? new SOAPClientFlash() : INSTANCE;
        return INSTANCE;
    }
    
    private SOAPClientFlash() {
        this.flashObject = DOM.createElement("object");
        DOM.setAttribute(
                this.flashObject, "classid",
                "clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
                );
        DOM.setAttribute(
                this.flashObject, "codebase",
                "http://fpdownload.macromedia.com/pub/shockwave/" +
                "cabs/flash/swflash.cab#version=8,0,0,0"
                );
        DOM.setAttribute(this.flashObject, "id", "SOAPClientFlash");
        DOM.setAttribute(this.flashObject, "width", "0px");
        DOM.setAttribute(this.flashObject, "height", "0px");
        
        Element param = DOM.createElement("param");
        DOM.setAttribute(param, "name", "allowScriptAccess");
        DOM.setAttribute(param, "value", "always");
        DOM.appendChild(this.flashObject, param);
        
        param = DOM.createElement("param");
        DOM.setAttribute(param, "name", "movie");
        DOM.setAttribute(param, "value", SOAPClientFlash.MOVIE);
        DOM.appendChild(this.flashObject, param);
        
        param = DOM.createElement("param");
        DOM.setAttribute(param, "name", "quality");
        DOM.setAttribute(param, "value", "high");
        DOM.appendChild(this.flashObject, param);
        
        param = DOM.createElement("param");
        DOM.setAttribute(param, "name", "bgcolor");
        DOM.setAttribute(param, "value", "#ffffff");
        DOM.appendChild(this.flashObject, param);
        
        this.flashEmbed = DOM.createElement("embed");
        DOM.setAttribute(this.flashEmbed, "src", SOAPClientFlash.MOVIE);
        DOM.setAttribute(this.flashEmbed, "quality", "high");
        DOM.setAttribute(this.flashEmbed, "bgcolor", "#ffffff");
        DOM.setAttribute(this.flashEmbed, "width", "0px");
        DOM.setAttribute(this.flashEmbed, "height", "0px");
        DOM.setAttribute(this.flashEmbed, "name", "SOAPClientFlash");
        DOM.setAttribute(this.flashEmbed, "allowScriptAccess", "always");
        DOM.setAttribute(this.flashEmbed, "type", "application/x-shockwave-flash");
        DOM.setAttribute(
                this.flashEmbed, "pluginspage", "http://www.macromedia.com/go/getflashplayer"
                );
        DOM.setAttribute(this.flashEmbed, "swLiveConnect", "true");
        
        DOM.appendChild(this.flashObject, this.flashEmbed);
        
        DOM.appendChild(RootPanel.getBodyElement(), this.flashObject);
        
        
        
    }
    
    public static void intialize( ReadyCallback callback ){
        SOAPClientFlash.getInstance();
        INSTANCE.intializeJavaScript( callback );
    }
    
    private native Element intializeJavaScript(ReadyCallback callback)/*-{
        $wnd.flashSOAPClientReadyNotify = function(){
           callback.@gwtip.servercom.client.SOAPClientFlash.ReadyCallback::onReady()();
        }                                                                                                                                                                                                                                                                          
        $wnd.soapAsyncCallbacks = new Array();
        $wnd.callbackOnSuccess = function(id, result ){
            asyncCallback = $wnd.soapAsyncCallbacks[ id ];                                                  
            asyncCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onSuccess(Ljava/lang/Object;)( result );
            $wnd.soapAsyncCallbacks[ id ] = null;                                                                                                                     
        };
        $wnd.callbackOnFail = function( id, fault ){
            asyncCallback = $wnd.soapAsyncCallbacks[ id ];
            asyncCallback.@com.google.gwt.user.client.rpc.AsyncCallback::onFailure(Ljava/lang/Throwable;)( 
                @gwtip.servercom.client.SOAPClientFlash::createSOAPFault(Ljava/lang/String;Ljava/lang/String;)( 
                     fault.faultString, fault.detail )
             );  
            $wnd.soapAsyncCallbacks[ id ] = null;                                                                                                                                   
        };                                                                                                                                                                                                                   
    }-*/;                                                      
    
    
    public void registerWebService( String alias, String wsdlUrl, RegisterCallback callback){
        this.registerWebService( System.currentTimeMillis(), alias, wsdlUrl, callback);
    }
    
    private native void registerWebService(
            long id, String alias, String wsdlUrl,
            RegisterCallback callback
            )/*-{
        
        $wnd.callbackOnLoad = function() {
            callback.@gwtip.servercom.client.SOAPClientFlash.RegisterCallback::onLoad()();
        };
        $wnd.callbackOnFault = function( fault ){
            callback.@gwtip.servercom.client.SOAPClientFlash.RegisterCallback::onFault(Lcom/google/gwt/core/client/JavaScriptObject;)( fault );
            
            
       };
       $wnd.document.SOAPClientFlash.registerWebService( alias, wsdlUrl );
    }-*/;
    
    public void callMethod( String alias, String methodName, JavaScriptObject args, AsyncCallback callback ){
        this.callMethod( System.currentTimeMillis(),
                alias, methodName, args, callback);
    }
    
    private native void callMethod( long id,
            String alias, String methodName, JavaScriptObject args, AsyncCallback callback )/*-{
        $wnd.soapAsyncCallbacks[ id ] = callback;                                                                             
        $wnd.document.SOAPClientFlash.callWebServiceMethod( id, alias, methodName, args, 'callbackOnSuccess', 'callbackOnFail' );                                                                            
    }-*/;
    
    private static void callSuccess( AsyncCallback callback, JavaScriptObject object ){
        callback.onSuccess( object );
    }
    
    private static SOAPFault createSOAPFault( String message, String detail ){
        return new SOAPFault(message, detail );
    }
    
    public static interface ReadyCallback{
        public void onReady();
    }
    
    public static interface RegisterCallback {
    
    public void onLoad();
    
    public void onFault( JavaScriptObject fault );
}
}
