package gwtip.sotu.client.ui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import gwtip.sotu.client.remote.ConversationServiceAsync;

/**
 *
 * @author rcooper
 */
public class LobbyPanel extends HorizontalPanel{
    
    /** Creates a new instance of LobbyPanel */
    public LobbyPanel( ) {
        super();
        this.add( new CreatePanel() );
        this.add( new JoinPanel() );
    }
    
}
