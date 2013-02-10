/*
 * Main.java
 *
 * Created on July 18, 2006, 6:37 PM
 *
 * Copyright Robert Cooper, Temple of the Screaming Penguin
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 */

package com.totsp.sotu.app;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author rcooper
 */
public class Main {
    
    public static ScreenAreaWatcher watcher = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Configuration config = new Configuration();
        ConfigurationDisplay display = new ConfigurationDisplay( config );
        display.setVisible( true );
        
        
    }
    
    
}
