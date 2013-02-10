
package com.manning.gwtip.javascript.scriptaculous.client;

import com.google.gwt.user.client.ui.UIObject;


public interface DropListener {
    
    public void onDrop( UIObject source, UIObject dropped );
    public void onHover( UIObject source, UIObject hovered );
}
