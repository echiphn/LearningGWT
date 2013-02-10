package gwtip.sotu.server;

import gwtip.sotu.client.remote.AccessException;
import gwtip.sotu.client.remote.ConversationDescriptor;
import gwtip.sotu.client.remote.SystemException;
import java.io.File;
import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author cooper
 */
public class ConversationServiceLocal {
    public static final ConversationDescriptor KEEP_ALIVE = new ConversationDescriptor();
    private static final User ADMIN_USER = new User();
    private final Timer keepAliveTimer = new Timer( true );
    private String rootDir;
    static {
        Random r = new Random();
        KEEP_ALIVE.name = "keepAlive";
        KEEP_ALIVE.adminPassword = Long.toString(r.nextLong());
        ADMIN_USER.username = Long.toString(r.nextLong());
        
    }
    
    HashMap<String, Conversation> conversations = new HashMap<String, Conversation>();
    HashMap<String, User> users = new HashMap<String, User>();
    
    
    
    /** Creates a new instance of ConversationService */
    public ConversationServiceLocal() {
        super();
        
        
    }
    
    public void init( String rootDir ) throws IOException{
        if( this.rootDir == null ){
            File root = new File(rootDir + File.separator + "conversations");
            root.mkdirs();
            this.rootDir = root.getAbsolutePath();
            
            try {
                this.createConversation(ADMIN_USER, KEEP_ALIVE);
            } catch(Exception ae) {
                ae.printStackTrace(); // this should never happen
            }
            
            keepAliveTimer.schedule( new TimerTask(){
                public void run(){
                    sendChatMessage( ADMIN_USER, KEEP_ALIVE, "ping");
                    
                    while( ADMIN_USER.sendQueue.poll() != null){
                        int i= 0;
                        i++;
                    }
                }
                
            }, 500, 3000);
        }
    }
    
    public ConversationDescriptor createConversation(
            User user, ConversationDescriptor descriptor
            ) throws AccessException, SystemException {
        
        Conversation check = conversations.get(descriptor.name);
        String finalName = descriptor.name;
        
        for(int i = 0; check != null; i++) {
            finalName = descriptor.name + i;
            check = conversations.get(finalName);
        }
        
        descriptor.name = finalName;
        try{
            Conversation conversation = new Conversation(descriptor, this.rootDir);
            conversations.put(descriptor.name, conversation);
            this.joinConversation(user, descriptor);
            
        } catch( IOException ioe ){
            throw new SystemException("Unable to open log file:"+ioe.toString());
        }
        
        return descriptor;
    }
    
    public void endConversation(User user, ConversationDescriptor descriptor)
    throws AccessException {
        Conversation conversation = conversations.get(descriptor.name);
        
        if(
                (conversation.descriptor.adminPassword != null) &&
                !conversation.descriptor.adminPassword.equals(
                descriptor.adminPassword
                )
                ) {
            throw new AccessException("Administration password rejected.");
        }
        
        try{
            conversation.log.flush();
            conversation.log.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        conversations.remove(descriptor.name);
        
        for(User u : conversation.users) {
            this.leaveConversation(u, conversation.descriptor);
        }
    }
    
    public void joinConversation(User user, ConversationDescriptor descriptor)
    throws AccessException {
        Conversation join = conversations.get(descriptor.name);
        if(
                (join.descriptor.joinPassword != null) &&
                !join.descriptor.joinPassword.equals(descriptor.joinPassword)
                ) {
            throw new AccessException(
                    "Incorrect password to join this channel."
                    );
        }
        
        User.SubscriptionObserver observer = user.createObserver(
                descriptor.name
                );
        join.observable.addObserver(observer);
        join.users.add(user);
        this.sendMessage(new JoinMessage(user, join));
    }
    
    public void leaveConversation(User user, ConversationDescriptor descriptor) {
        Conversation leave = conversations.get(descriptor.name);
        User.SubscriptionObserver observer = user.subscriptions.remove(
                descriptor.name
                );
        leave.observable.deleteObserver(observer);
        leave.users.remove(user);
        
        Message message = new LeaveMessage(user, leave);
        this.sendMessage(message);
        if( users.size() == 0){
            try{
                this.endConversation( user, leave.descriptor );
            } catch(AccessException e){
                ;// this shouldn't happen;
            }
        }
    }
    
    public ConversationDescriptor[] listConversations() {
        Collection<Conversation> values = conversations.values();
        ConversationDescriptor[] results = new ConversationDescriptor[values.size() -1];
        int count = 0;
        
        for(Conversation c : values) {
            if( c.descriptor == ConversationServiceLocal.KEEP_ALIVE ){
                continue;
            }
            results[count] = new ConversationDescriptor();
            results[count].name = c.descriptor.name;
            
            if(c.descriptor.joinPassword != null) {
                results[count].joinPassword = "private";
            }
            
            count++;
        }
        
        return results;
    }
    
    public boolean sendChatMessage(
            User user, ConversationDescriptor descriptor, String text
            ) {
        Conversation conversation = conversations.get(descriptor.name);
        
        if(conversation.users.contains(user)) {
            this.sendMessage(new ChatMessage(user, conversation, text));
            
            return true;
        } else {
            return false;
        }
    }
    
    boolean sendImageMessage(
            ConversationDescriptor descriptor, String url
            ) {
        Conversation conversation = conversations.get(descriptor.name);
        
        
        this.sendMessage(new ImageMessage(conversation, url));
        return true;
    }
    
    void sendMessage(Message message) {
        Conversation conversation = conversations.get(message.conversation);
        conversation.observable.notifyObservers(message);
        try{
            if( conversation.descriptor != ConversationServiceLocal.KEEP_ALIVE ){
                conversation.log.write( ":"+ message.time +
                        ":"+
                        message.message +
                        "\n" );
                conversation.log.flush();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void signOff(User user) {
        for(String name : user.subscriptions.keySet()) {
            ConversationDescriptor d = new ConversationDescriptor();
            d.name = name;
            this.leaveConversation(user, d);
        }
        users.remove( user.username );
    }
    
    public User signOn(String username) {
        User user = new User();
        user.username = username;
        users.put(username, user);
        
        return user;
    }
    
    public ConversationDescriptor playback( User user, long conversationId, long startPosition ) throws AccessException, SystemException {
        ConversationDescriptor d = new ConversationDescriptor();
        try{
            File log = new File( rootDir, conversationId+".txt");
            if(!log.exists()){
                throw new SystemException("Conversation does not exist.");
            }
            List<String> lines = FileUtils.readLines(log , "UTF-8");
            d.name = "Playback "+ System.currentTimeMillis();
            Random r = new Random();
            d.joinPassword = Long.toString( r.nextLong() );
            d.adminPassword = Long.toString( r.nextLong() );
            this.createConversation( ConversationServiceLocal.ADMIN_USER, d );
            this.joinConversation( user, d );
            PlaybackThread playback = new PlaybackThread( d, lines );
            playback.start();
            
        } catch(IOException ioe){
            ioe.printStackTrace();
            throw new SystemException("Unable to read conversation log.");
        }
        return d;
        
    }
    
    private class PlaybackThread extends Thread {
        private ConversationDescriptor descriptor;
        private List<String> lines;
        PlaybackThread( ConversationDescriptor descriptor, List<String> lines ){
            this.descriptor = descriptor;
        }
        
        public void run(){
            StringBuffer currentMessage = new StringBuffer();
            String nextLine = null;
            Iterator<String> lit = lines.iterator();
            Message previousMessage = new Message();
            previousMessage.time = 0;
            if( lit.hasNext() ){
                currentMessage.append( lit.next() );
            }
            while( lit.hasNext() && conversations.get(descriptor.name) != null ){
                nextLine = lit.next();
                while( !nextLine.startsWith("#") ){
                    currentMessage.append( nextLine );
                    nextLine = lit.hasNext() ? lit.next() : nextLine;
                }
                String parse = currentMessage.toString();
                
                Message send = new Message();
                send.conversation = descriptor.name;
                send.message = parse.substring( parse.indexOf("#", 2) + 1, parse.length() );
                send.time = Long.parseLong(parse.substring( 1, parse.indexOf("#",2)));
                try{
                    Thread.sleep( send.time = previousMessage.time );
                } catch(InterruptedException e){
                    ;//do nothing
                }
                
                sendMessage( send );
                
                previousMessage = send;
            }
            try{
                endConversation( ConversationServiceLocal.ADMIN_USER, descriptor);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
