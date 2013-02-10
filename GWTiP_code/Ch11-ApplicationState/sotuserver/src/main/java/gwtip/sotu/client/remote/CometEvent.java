package gwtip.sotu.client.remote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class CometEvent {
    
    public static final int JOIN_TYPE = 0;
    public static final int LEAVE_TYPE = 1;
    public static final int IMAGE_TYPE = 2;
    public static final int CHAT_TYPE = 3;
    
    public String username;
    public int type;
    public String value;
    public long time;
    
    public CometEvent(String xml) {
        GWT.log( xml, null );
        Document doc = XMLParser.parse( xml );
        Element root = (Element) doc.getFirstChild();
        GWT.log( root.toString(), null );
        username = root.getElementsByTagName("user").item(0).getFirstChild().getNodeValue();
        time = Long.parseLong( root.getElementsByTagName("time").item(0).getFirstChild().getNodeValue() );
        NodeList messages = root.getElementsByTagName( "value" );
        if( messages!= null && messages.getLength() > 0 ){
            this.value = messages.item(0).getFirstChild().getNodeValue();
        }
        if( root.getNodeName().equals("join") ){
            this.type = CometEvent.JOIN_TYPE;
        } else if( root.getNodeName().equals("image") ) {
            this.type = CometEvent.IMAGE_TYPE;
        } else if( root.getNodeName().equals("leave") ) {
            this.type = CometEvent.LEAVE_TYPE;
        } else if( root.getNodeName().equals("chat") ) {
            this.type = CometEvent.CHAT_TYPE;
        } else {
            throw new RuntimeException( "Unknown type.");
        }
        
    }
    
}
