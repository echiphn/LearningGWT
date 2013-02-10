package gwtip.sotu.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import gwtip.sotu.client.Controller;
import gwtip.sotu.client.remote.ConversationDescriptor;

public class ChatEntry extends DockPanel {
    
    private Controller controller = Controller.getInstance();
    private ConversationDescriptor descriptor;
    private TextArea entry;
    private Button send;
    
    public ChatEntry(ConversationDescriptor descriptor) {
        super();
        this.setStyleName( "chat-entry" );
        this.descriptor = descriptor;
        this.entry = new TextArea();
        this.entry.setStyleName( "entry-box" );
        this.entry.addKeyboardListener( new KeyboardListener(){
            public void onKeyPress(Widget sender, char keyCode, int modifiers) {
                if( keyCode == KeyboardListener.KEY_ENTER &&
                        (modifiers & KeyboardListener.KEY_SHIFT)
                        != KeyboardListener.KEY_SHIFT ){
                    send();
                }
            }
            
            public void onKeyUp(Widget sender, char keyCode, int modifiers) {
                //do nothing;
            }
            
            public void onKeyDown(Widget sender, char keyCode, int modifiers) {
                //do nothing;
            }
            
        });
        this.send = new Button("Send", new ClickListener(){
            public void onClick(Widget sender) {
                send();
            }
            
        });
        this.add( this.entry, CENTER);
        this.add( this.send, WEST );
    }
    
    private void send(){
        if( entry.getText().trim().length() != 0 ){
            controller.sendChatMessage( descriptor, entry.getText() );
            entry.setText("");
        }
    }
}
