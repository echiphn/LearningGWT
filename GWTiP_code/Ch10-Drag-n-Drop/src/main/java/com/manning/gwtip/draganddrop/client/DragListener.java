/*
 * DragListener.java
 *
 * Created on December 17, 2006, 10:35 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.manning.gwtip.draganddrop.client;

import com.google.gwt.user.client.ui.SourcesMouseEvents;

/**
 *
 * @author cooper
 */
public interface DragListener {
    
    public void onStart( SourcesMouseEvents source );
    public void onDrag( SourcesMouseEvents source );
    public void onEnd( SourcesMouseEvents source );
    
}
