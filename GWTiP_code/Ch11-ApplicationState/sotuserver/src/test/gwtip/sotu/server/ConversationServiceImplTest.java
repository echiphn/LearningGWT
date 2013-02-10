/*
 * ConversationServiceImplTest.java
 * JUnit based test
 *
 * Created on October 8, 2006, 9:50 AM
 */

package gwtip.sotu.server;

import junit.framework.*;
import gwtip.sotu.client.remote.ConversationDescriptor;
import java.io.File;

/**
 *
 * @author cooper
 */
public class ConversationServiceImplTest extends TestCase {
    
    public ConversationServiceImplTest(String testName) {
        super(testName);
    }

    public void testDummy(){
        
    }
    
    public void xtestSignOn() throws Exception{
        System.out.println("signOn");
        
        String username = "test";
        ConversationServiceLocal instance = new ConversationServiceLocal();
        instance.init(System.getProperty("basedir")+File.separator+"target");
        
        User expResult = new User( "test" );
        User result = instance.signOn(username);
        assertEquals(expResult.username, result.username);
        
        
    }

    public void xtestSignOff() throws Exception{
        System.out.println("signOff");
        
        
        ConversationServiceLocal instance = new ConversationServiceLocal();
        instance.init(System.getProperty("basedir")+File.separator+"target");
        User user = instance.signOn( "test2");
        
        instance.signOff(user);
        this.assertNull( instance.users.get("test2"));
        
    }

    public void xtestConversation() throws Exception {
        System.out.println("joinConversation");
        
        ConversationServiceLocal instance = new ConversationServiceLocal();
        instance.init(System.getProperty("basedir")+File.separator+"target");
        User user = instance.signOn("test3");
        ConversationDescriptor descriptor = new ConversationDescriptor();
        descriptor.name = "Test Channel";
        descriptor.adminPassword = "foobar";
        descriptor = instance.createConversation( user, descriptor );
        
        User user2 = instance.signOn("test4");
        instance.joinConversation( user2, descriptor );
        
        instance.sendChatMessage( user2, descriptor, "This is a test");
        
        Message m = user.sendQueue.poll();
        m = user.sendQueue.poll(); // ignore my welcome message 
        m = user.sendQueue.poll(); // ignore user2's welcome message
        
        Message m2 = user2.sendQueue.poll();
        m2 = user2.sendQueue.poll();
        
        this.assertNotNull( m );
        this.assertNotNull( m2 ); // ignore my welcome message
        this.assertTrue( m == m2 ); 
        System.out.println( m );
        System.out.println( m2 );
        instance.signOff( user2 );
        System.out.println( user.sendQueue.poll() ); // user2's signoff. 
        
    }

    
}
