package gwtip.sotu.client.remote;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 * @author rcooper
 */
public interface ConversationService extends RemoteService {
    
    public ConversationDescriptor[] listConversations();
    
    public ConversationDescriptor startConversation(ConversationDescriptor conversation) 
        throws AccessException, SystemException;
    
    public boolean joinConversation(ConversationDescriptor conversation) throws AccessException;
    
    public boolean leaveConversation(ConversationDescriptor conversation) throws SystemException;
    
    public boolean endConversation(ConversationDescriptor conversation) throws AccessException;
    
    public boolean sendChatMessage(ConversationDescriptor conversation, String message);
    
    public ConversationDescriptor playback( long conversationId, long startPosition ) 
        throws AccessException, SystemException;
}
