package gwtip.sotu.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;


public class User {
    
    
    public String username;
    public Queue<Message> sendQueue = new LinkedList<Message>();
    HashMap<String, SubscriptionObserver> subscriptions = 
            new HashMap<String,SubscriptionObserver>();
    
    public User() {
        super();
    }
    
    public User( String username ){
        super();
        this.username = username;
    }
    
    SubscriptionObserver createObserver( String name ){
        SubscriptionObserver o = new SubscriptionObserver(name);
        subscriptions.put( name, o );
        return o;
    }
    
    public class SubscriptionObserver implements Observer {
        
        public String name;
        
        protected SubscriptionObserver(String name ){
            this.name = name;
        }

        public void update(Observable observable, Object object) {
            Message m = (Message) object;
            if( m.conversation.equals(this.name) ){
                sendQueue.add( (Message) object );
            }
        }
        
        
    }
    
    
    
}
