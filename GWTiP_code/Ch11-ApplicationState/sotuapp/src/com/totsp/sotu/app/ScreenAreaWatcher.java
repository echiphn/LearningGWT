/*
 * ScreenWatcher.java
 *
 * Created on July 18, 2006, 6:42 PM
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

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import java.util.Timer;
import java.util.TimerTask;
import javax.media.jai.JAI;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.event.MouseInputAdapter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.MultipartPostMethod;


/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ScreenAreaWatcher {
    private static final Color GREEN = new Color(0, 255, 0);
    private BufferedImage image = null;
    private BufferedImageComparator comp = new BufferedImageComparator();
    private Configuration config;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private Timer timer = new Timer(true);
    private JWindow[] windows;
    
    /** Creates a new instance of ScreenWatcher */
    public ScreenAreaWatcher(final Configuration config) throws AWTException {
        super();
        this.config = config;
        System.out.println( config.getWidth() +"x" + config.getHeight());
        JPanel jp = null;
        
        JWindow top = new JWindow();
        top.setBackground(GREEN);
        top.setLocation(0, 0);
        top.setAlwaysOnTop(true);
        jp = new JPanel();
        jp.setBackground(GREEN);
        top.add(jp);
        top.pack();
        top.setSize(config.getWidth(), config.getBorderSize());
        
        JWindow bottom = new JWindow();
        bottom.setBackground(GREEN);
        bottom.setLocation(0, config.getHeight() - config.getBorderSize());
        bottom.setAlwaysOnTop(true);
        jp = new JPanel();
        jp.setBackground(GREEN);
        bottom.add(jp);
        bottom.pack();
        bottom.setSize(config.getWidth(), config.getBorderSize());
        
        JWindow left = new JWindow();
        left.setBackground(GREEN);
        left.setLocation(0, 0);
        left.setAlwaysOnTop(true);
        jp = new JPanel();
        jp.setBackground(GREEN);
        left.add(jp);
        left.pack();
        left.setSize(config.getBorderSize(), config.getHeight());
        
        JWindow right = new JWindow();
        right.setLocation(config.getWidth() - config.getBorderSize(), 0);
        right.setAlwaysOnTop(true);
        jp = new JPanel();
        jp.setBackground(GREEN);
        right.add(jp);
        right.pack();
        right.setSize(config.getBorderSize(), config.getHeight());
        
        top.setVisible(true);
        bottom.setVisible(true);
        left.setVisible(true);
        right.setVisible(true);
        
        windows = new JWindow[4];
        windows[0] = top;
        windows[1] = bottom;
        windows[2] = left;
        windows[3] = right;
        
        MouseInputAdapter adapter = new Adapter(windows);
        
        top.addMouseListener(adapter);
        top.addMouseMotionListener(adapter);
        bottom.addMouseListener(adapter);
        bottom.addMouseMotionListener(adapter);
        left.addMouseListener(adapter);
        left.addMouseMotionListener(adapter);
        right.addMouseListener(adapter);
        right.addMouseMotionListener(adapter);
        
        timer.schedule(
                new ImageUpdateTimerTask(this), 0, config.getMaxUpdateInterval()
                );
        
        this.addPropertyChangeListener( "image", new PropertyChangeListener(){
            HttpClient client = new HttpClient();
            
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                BufferedImage img = (BufferedImage) propertyChangeEvent.getNewValue();
                try{
                    File outputFile = File.createTempFile("save-"+System.currentTimeMillis(), ".png");
                    JAI.create("filestore", img, outputFile.getCanonicalPath(), "PNG", null);
                    System.out.println( "Temp File:  "+ outputFile.getCanonicalPath() );
                    MultipartPostMethod method = new MultipartPostMethod(config.getServerUrl() + "/ImagePublishingServlet");
                    method.addParameter("adminPassword", config.getAdminPassword() );
                    method.addParameter( "conversation", config.getConversationName() );
                    method.addParameter( "image", outputFile );
                    client.executeMethod( method );
                    
                    
                } catch(Exception e){
                    System.err.println("Error handling image");
                    e.printStackTrace();
                }
            }
            
        });
    }
    
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener l) {
        changes.addPropertyChangeListener(propertyName, l);
    }
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
    
    public void setImage(BufferedImage image) {
        BufferedImage old = this.image;
        this.image = image;
        
        if(comp.compare(image, old) != 0) {
            changes.firePropertyChange("image", old, image);
        }
    }
    
    public void stop() {
        timer.cancel();
        
        for(JWindow w : windows) {
            w.setVisible(false);
        }
        
        PropertyChangeListener[] listeners =
                changes.getPropertyChangeListeners();
        
        for(PropertyChangeListener l : listeners) {
            changes.removePropertyChangeListener(l);
        }
    }
    
    private static class Adapter extends MouseInputAdapter {
        private JWindow[] windows;
        private int xStart = 0;
        private int yStart = 0;
        
        public Adapter(JWindow[] windows) {
            this.windows = windows;
        }
        
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            
            Point p = null;
            
            for(JWindow w : windows) {
                p = w.getLocationOnScreen();
                
                int newX = (int) p.getX() + (e.getX() - xStart);
                int newY = (int) p.getY() + (e.getY() - yStart);
                w.setLocation(newX, newY);
            }
            
            xStart = e.getX();
            yStart = e.getY();
        }
        
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            xStart = e.getX();
            yStart = e.getY();
        }
    }
    
    private static class ImageUpdateTimerTask extends TimerTask {
        private Robot robot;
        private ScreenAreaWatcher watcher;
        
        public ImageUpdateTimerTask(ScreenAreaWatcher watcher)
        throws AWTException {
            this.watcher = watcher;
            this.robot = new Robot();
        }
        
        public void run() {
            Rectangle rect = new Rectangle(
                    (int) watcher.windows[0].getLocationOnScreen().getX(),
                    (int) watcher.windows[0].getLocationOnScreen().getY(),
                    watcher.config.getWidth(), watcher.config.getHeight()
                    );
            
            BufferedImage image = robot.createScreenCapture(rect);
            watcher.setImage(image);
        }
    }
}
