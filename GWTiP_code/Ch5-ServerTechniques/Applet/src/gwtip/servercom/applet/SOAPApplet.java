package gwtip.servercom.applet;
import gwtip.servercom.soap.HelloService;
import gwtip.servercom.soap.HelloServicePortType;
import gwtip.servercom.soap.Person;
import java.applet.Applet;
import javax.swing.JTextArea;
import netscape.javascript.JSObject;

public class SOAPApplet extends Applet {
    
    private JTextArea box  = new JTextArea();
    
    /** Creates a new instance of Applet */
    public SOAPApplet() {
        super();
    }
    public void start(){
        JSObject window = JSObject.getWindow( this );
        window.eval( "appletSOAPClientReadyNotify();");
        
        this.add( box );
        box.setText( "Loaded");
    }
    
    public String sayHello(){
        box.setText( "sayHello");
        //HelloService client = new HelloService();
        //HelloServicePortType service =  client.getHelloServiceHttpPort();
        
        JSObject window = JSObject.getWindow( this );
        JSObject person = (JSObject) window.getMember("sayHelloPerson");
        Person p = new Person();
        p.setFirstName( (String) person.getMember("firstName") );
        p.setLastName( (String) person.getMember("lastName") );
        //String result = service.sayHello( p );
        String result="temp";
        String[] callbackArgs = { result };
        
        return result;
    }
    
}
