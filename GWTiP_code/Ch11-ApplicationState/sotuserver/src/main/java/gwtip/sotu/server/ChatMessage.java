package gwtip.sotu.server;

public class ChatMessage extends Message {
    
    public ChatMessage(User user, Conversation conversation, String text) {
        this.time= System.currentTimeMillis() - conversation.startTime;
        StringBuffer message = new StringBuffer("<chat>");
        message.append("<user>"); 
        message.append(user.username);
        message.append("</user>");
        message.append("<time>");
        message.append( this.time );
        message.append("</time>");
        message.append("<value><![CDATA[");
        message.append(text);
        message.append("]]></value>");
        message.append("</chat>");
        this.message = message.toString();
        this.conversation = conversation.descriptor.name;
    }
    
}
