
package gwtip.sotu.server;

/**
 *
 * @author cooper
 */
public class ImageMessage extends Message {
    
    /** Creates a new instance of ImageMessage */
    public ImageMessage(Conversation conversation, String url) {
        this.time= System.currentTimeMillis() - conversation.startTime;
        StringBuffer message = new StringBuffer("<image>");
        message.append("<user>");
        message.append("image");
        message.append("</user>");
        message.append("<time>");
        message.append( this.time  );
        message.append("</time>");
        message.append("<value>");
        message.append(url);
        message.append("</value>");
        message.append("</image>");
        this.message = message.toString();
        this.conversation = conversation.descriptor.name;
    }
    
    
}
