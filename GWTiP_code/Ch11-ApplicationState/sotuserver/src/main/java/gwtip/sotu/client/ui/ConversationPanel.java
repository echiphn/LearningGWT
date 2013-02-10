package gwtip.sotu.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtip.sotu.client.Controller;
import gwtip.sotu.client.remote.Comet;
import gwtip.sotu.client.remote.ConversationDescriptor;

/**
 *
 * @author cooper
 */
public class ConversationPanel extends HorizontalPanel{
    
    private  Comet comet;
    private  ConversationDescriptor descriptor;
    private  CometImage image;
    private  ChatDisplay chat;
    private  ChatEntry entry;
    private  Button leave = new Button("Leave", new ClickListener(){
        public void onClick( Widget w){
            Controller.getInstance().leaveConversation( descriptor );
        }
    });
    /** Creates a new instance of ConversationPanel */
    public ConversationPanel(ConversationDescriptor descriptor, Comet comet) {
        super();
        this.comet = comet;
        this.descriptor = descriptor;
        
        VerticalPanel v = new VerticalPanel();
        this.chat = new ChatDisplay();
        comet.addCometListener( descriptor.name, this.chat );
        v.add( chat);
        this.entry = new ChatEntry( descriptor );
        v.add( entry );
        this.image = new CometImage();
        comet.addCometListener( descriptor.name, image );
        this.add( image );
        this.add(v);
    }
    
}
