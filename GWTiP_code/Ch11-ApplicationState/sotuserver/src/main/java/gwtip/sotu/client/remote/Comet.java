package gwtip.sotu.client.remote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Comet {
    private static final String STREAM_PATH = GWT.getModuleBaseURL() +
        "/cometStream";
    private HashMap listeners = new HashMap();
    private String streamingServicePath;
    private boolean keepAlive = false;

    public Comet(String username) {
        this.addCallbackMethod(this);
        this.streamingServicePath = STREAM_PATH + "?username=" + username;
        this.restartIFrame();

        Timer t = new Timer() {
                public void run() {
                    if(!keepAlive) {
                        restartIFrame();
                    }

                    keepAlive = false;
                }
            };

        t.scheduleRepeating(60000);
    }

    private native void addCallbackMethod(Comet thisInstance) /*-{
    $wnd.cometCallback = function(conversation, value)
    {
    var val = thisInstance.@gwtip.sotu.client.remote.Comet::notify(Ljava/lang/String;Ljava/lang/String;)(conversation,value);

    }
    }-*/;

    public void addCometListener(String name, CometListener listener) {
        EventSupport support = (EventSupport) listeners.get(name);

        if(support == null) {
            support = new EventSupport();
        }

        support.add(listener);
        listeners.put(name, support);
    }

    public void clearCometListeners(String name) {
        EventSupport support = (EventSupport) listeners.get(name);

        if(support != null) {
            support.clear();
        }
    }

    private boolean notify(String name, String message) {
        EventSupport es = (EventSupport) listeners.get(name);

        if(es != null) {
            CometEvent event = new CometEvent(message);
            es.fireEvent(event);
        }

        return true;
    }

    public boolean removeCometListener(String name, CometListener listener) {
        boolean value = false;
        EventSupport support = (EventSupport) listeners.get(name);

        if(support != null) {
            value = support.remove(listener);
        }

        return value;
    }

    private void restartIFrame() {
        Element iframe = DOM.getElementById("__gwt_cometFrame");

        if(iframe == null) {
            iframe = DOM.createIFrame();
            DOM.setAttribute(iframe, "id", "__gwt_cometFrame");
            //TODO change these
            DOM.setStyleAttribute(iframe, "width", "0");
            DOM.setStyleAttribute(iframe, "height", "0");
            DOM.setStyleAttribute(iframe, "border", "0");

            DOM.appendChild(RootPanel.getBodyElement(), iframe);
        }

        DOM.setAttribute(iframe, "src", streamingServicePath);
    }

    private static class EventSupport extends ArrayList {
        public void fireEvent(CometEvent event) {
            Iterator it = this.iterator();

            while(it.hasNext()) {
                CometListener listener = (CometListener) it.next();
                listener.onEvent(event);
            }
        }
    }
}
