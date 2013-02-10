package gwtip.sotu.client.remote;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author rcooper
 */
public interface ConversationServiceAsync {
    
    public void listConversations(AsyncCallback callback);
    
    public void startConversation(ConversationDescriptor conversation, AsyncCallback callback);
    
    public void joinConversation(ConversationDescriptor conversation, AsyncCallback callback);
    
    public void leaveConversation(ConversationDescriptor conversation, AsyncCallback callback);
    
    public void endConversation(ConversationDescriptor conversation, AsyncCallback callback);
    
    public void sendChatMessage(ConversationDescriptor conversation, String message, AsyncCallback callback);

    public void playback( long conversationId, long startPosition, AsyncCallback callback );
}
