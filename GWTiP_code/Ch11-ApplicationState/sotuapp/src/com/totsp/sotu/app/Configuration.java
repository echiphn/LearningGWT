/*
 * Configuration.java
 *
 * Created on July 18, 2006, 6:39 PM
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

import java.beans.PropertyChangeSupport;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Configuration {
    
    
    private String serverUrl = "http://localhost:8080/sotu-server/";
    private String conversationName;
    private int width = 640;
    private int height = 480;
    private int borderSize = 3;
    private long maxUpdateInterval = 2000;
    private PropertyChangeSupport changes = new PropertyChangeSupport( this );
    private String adminPassword;
    
    /** Creates a new instance of Configuration */
    public Configuration() {
        super();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        String old = this.serverUrl;
        this.serverUrl = serverUrl;
        changes.firePropertyChange( "serverUrl", old, serverUrl );
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        String old = this.conversationName;
        this.conversationName = conversationName;
        changes.firePropertyChange( "conversationName", old, conversationName );
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        int old = this.width;
        this.width = width;
        changes.firePropertyChange( "width", old, width );
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        int old = this.height;
        this.height = height;
        changes.firePropertyChange("height", old, height );
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        int old = this.borderSize;
        this.borderSize = borderSize;
        changes.firePropertyChange( "borderSize", old, borderSize );
    }

    public long getMaxUpdateInterval() {
        return maxUpdateInterval;
    }

    public void setMaxUpdateInterval(long maxUpdateInterval) {
        long old = this.maxUpdateInterval;
        this.maxUpdateInterval = maxUpdateInterval;
        changes.firePropertyChange("maxUpdateInterval", old, maxUpdateInterval );
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
}
