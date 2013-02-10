package gwtip.sotu.server;

import gwtip.sotu.client.remote.ConversationDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author cooper
 */
public class Conversation {
    
    public ConversationDescriptor descriptor;
    public Writer log;
    public Observable observable = new Observable(){
        public void notifyObservers(Object object) {
            super.setChanged();
            super.notifyObservers(object);
        }
    };
    
    public long startTime = System.currentTimeMillis();
    public ArrayList<User> users = new ArrayList<User>();
    /** Creates a new instance of Conversation */
    public Conversation(ConversationDescriptor descriptor, String rootDir) throws IOException{
        super();
        this.descriptor = descriptor;
        this.descriptor.id = this.startTime;
        this.log = new FileWriter(rootDir+File.separator+ this.startTime);
        
    }
    
}
