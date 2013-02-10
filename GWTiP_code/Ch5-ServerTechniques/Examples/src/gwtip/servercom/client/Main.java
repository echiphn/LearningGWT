/*
 * Main.java
 *
 * Created on November 3, 2006, 5:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gwtip.servercom.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.HTTPRequest;
import com.google.gwt.user.client.ResponseTextHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

/**
 *
 * @author cooper
 */
public class Main implements EntryPoint {
    
    
    public Main() {
    }
    
    public void onModuleLoad() {
        
        HTTPRequest.asyncGet( GWT.getModuleBaseURL()+"/person.xml",
                new ResponseTextHandler(){
            public void onCompletion(String responseText) {
                Window.alert( responseText );
                Document doc = XMLParser.parse( responseText);
                Element root = (Element) doc.getFirstChild();
                String firstName = root
                        .getElementsByTagName("firstName")
                        .item(0)
                        .getFirstChild()
                        .getNodeValue();
                String lastName = root
                        .getElementsByTagName("lastName")
                        .item(0)
                        .getFirstChild()
                        .getNodeValue();
                Window.alert( "Hello "+ firstName + " "+ lastName );
            }
            
        });
        
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
                GWT.getModuleBaseURL()+"/person.js");
        
        try {
            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    Window.alert( "There was an error! "+exception.getMessage() );
                }
                
                public void onResponseReceived(Request request, Response response) {
                    JSONObject person = (JSONObject) JSONParser.parse(response.getText());
                    String message = "Found person: "+
                            ((JSONString)person.get( "firstName")).stringValue() + " "+
                            ((JSONString)person.get( "lastName")).stringValue();
                    Window.alert( message );
                }
            });
        } catch (RequestException e) {
            Window.alert("Unable to build the request.");
        }
        
        SOAPClientFlash.intialize( new SOAPClientFlash.ReadyCallback(){
            
            public void onReady(){
                RootPanel.get().add( new Label( "Getting WebSerivce.") );
                SOAPClientFlash client = SOAPClientFlash.getInstance();
                client.registerWebService( "hello", "http://localhost:8080/gwtipXfire/services/HelloService?wsdl",
                        new SOAPClientFlash.RegisterCallback(){
                    public void onFault(JavaScriptObject fault) {
                        Window.alert( "Register Fault:"+fault.toString() );
                    }
                    
                    public void onLoad() {
                        RootPanel.get().add( new Label( "Got WebSerivce.") );
                        JavaScriptObjectDecorator person = new JavaScriptObjectDecorator();
                        person.setStringProperty( "firstName", "John");
                        person.setStringProperty( "lastName", "Doe");
                        JavaScriptObjectDecorator args = new JavaScriptObjectDecorator(
                                JavaScriptObjectDecorator.newArray()
                                );
                        args.setJavaScriptObjectProperty("0", person.getObject());
                        SOAPClientFlash client = SOAPClientFlash.getInstance();
                        
                        client.callMethod( "hello", "sayHello", args.getObject(), new AsyncCallback(){
                            public void onSuccess(Object result) {
                                Window.alert( result.toString() );
                            }
                            
                            public void onFailure(Throwable caught) {
                                Window.alert( caught.getMessage() );
                            }
                            
                        });
                    }
                    
                });
            }
        });
    }
    
}
