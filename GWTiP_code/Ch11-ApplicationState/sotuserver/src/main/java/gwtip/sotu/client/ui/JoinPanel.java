package gwtip.sotu.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtip.sotu.client.Controller;
import gwtip.sotu.client.remote.ConversationDescriptor;
import gwtip.sotu.client.remote.ConversationServiceAsync;

/**
 *
 * @author rcooper
 */
public class JoinPanel extends VerticalPanel {
    
    private Label joinPasswordLabel = new Label("Join Password:");
    private PasswordTextBox joinPassword= new PasswordTextBox();
    private Button joinButton = new Button("Join", new ClickListener(){
        public void onClick(Widget sender) {
            Controller.getInstance().joinConversation( selected ); 
        }
        
    });
    private ConversationList list;
    private ConversationDescriptor selected;
    /** Creates a new instance of JoinPanel */
    public JoinPanel() {
        super();
        list = new ConversationList();
        this.add( new Label("Select a Conversation:") );
        list.addClickListener( new ClickListener(){
            public void onClick(Widget sender) {
                ConversationList list = (ConversationList) sender;
                remove( joinPasswordLabel );
                remove( joinPassword );
                remove( joinButton );
                int i = list.getSelectedIndex();
                if( i != -1 ){
                    if( list.descriptors[i].joinPassword != null ){
                        add( joinPasswordLabel );
                        add( joinPassword );
                    } else {
                        joinPassword.setText("");
                    }
                    add( joinButton );
                    selected = list.descriptors[i];
                }
            }
        });
        this.add( list );        
    }
    
}
