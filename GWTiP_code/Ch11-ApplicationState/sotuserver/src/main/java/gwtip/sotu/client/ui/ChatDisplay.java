package gwtip.sotu.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwtip.sotu.client.remote.CometEvent;
import gwtip.sotu.client.remote.CometListener;

public class ChatDisplay extends ScrollPanel implements CometListener {
    
    private VerticalPanel innerPanel = new VerticalPanel();
    
    public ChatDisplay() {
        super();
        this.add( innerPanel );
        this.setStyleName("chat-display");
        this.setHeight( "300px" );
        this.setWidth( "400px");
        
    }
    
    public void onEvent(CometEvent event) {
       
            HTML html = new HTML( "<span class='user'>"+ event.username +"</span> " +
                    "<span class='value'>"+event.value+"</span>");
            switch( event.type ){
                case CometEvent.CHAT_TYPE:
                    html.setStyleName("chat");
                    break;
                case CometEvent.JOIN_TYPE:
                    html.setStyleName("join");
                    break;
                case CometEvent.LEAVE_TYPE:
                    html.setStyleName("leave");
                    break;
                case CometEvent.IMAGE_TYPE:
                    return;
            }
            
            innerPanel.add( html );
            this.setScrollPosition( innerPanel.getOffsetHeight() );
    }
    
}
