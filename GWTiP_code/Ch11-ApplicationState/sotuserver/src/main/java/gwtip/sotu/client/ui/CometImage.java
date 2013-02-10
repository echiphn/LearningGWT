package gwtip.sotu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
import gwtip.sotu.client.remote.CometEvent;
import gwtip.sotu.client.remote.CometListener;

/**
 *
 * @author rcooper
 */
public class CometImage extends Image implements CometListener {
    
    /** Creates a new instance of ConversationImage */
    public CometImage() {
        super();
       
    }

    public void onEvent(CometEvent event) {
        if( event.type == CometEvent.IMAGE_TYPE ){
            this.setUrl( event.value );
            GWT.log( "New URL:"+event.value, null);
        }
    }
    
}
