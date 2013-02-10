package gwtip.sotu.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import gwtip.sotu.client.remote.Comet;
import gwtip.sotu.client.remote.CometEvent;
import gwtip.sotu.client.remote.CometListener;
import gwtip.sotu.client.remote.ConversationDescriptor;
import gwtip.sotu.client.remote.ConversationService;
import gwtip.sotu.client.remote.ConversationServiceAsync;
import gwtip.sotu.client.ui.ConversationPanel;
import gwtip.sotu.client.ui.LobbyPanel;


public class Controller {
    private static Controller instance;
    public static ConversationServiceAsync service;
    public static Comet comet;
    private ConversationPanel conversation;
    private LobbyPanel lobby;
    public ConversationDescriptor currentConversation;
    private boolean ignoreHistory = false;
    
    private Controller() {
        super();
        service = (ConversationServiceAsync) GWT.create(ConversationService.class);
        
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(
                GWT.getModuleBaseURL() +
                "/conversationService");
        
        History.addHistoryListener( new HistoryListener(){
            public void onHistoryChanged(String token){
                if( !ignoreHistory )
                    handleToken( token );
            }
        });
    }
    
    boolean handleToken(String token ){
        if( token != null &&
                token.indexOf("|") != -1  ){
            long conversationId = Long.parseLong(
                    token.substring( 0,
                    token.indexOf("|") ) );
            if( currentConversation == null ||
                    currentConversation.id == conversationId ){
                long startPosition = Long.parseLong(
                        token.substring(
                        token.indexOf("|") + 1,
                        token.length() ) );
                if( currentConversation != null)
                    leaveConversation( currentConversation );
                playback( conversationId, startPosition );
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static Controller getInstance() {
        return instance = (instance == null) ? new Controller() : instance;
    }
    
    public void createConversation(ConversationDescriptor descriptor) {
        RootPanel p = RootPanel.get();
        p.remove(this.lobby);
        service.startConversation(
                descriptor,
                new AsyncCallback() {
            public void onSuccess(Object result) {
                ConversationDescriptor d = (ConversationDescriptor) result;
                conversation = new ConversationPanel(d, comet);
                RootPanel.get().add( conversation );
            }
            
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
        
    }
    
    public void joinConversation(final ConversationDescriptor descriptor) {
        
        service.joinConversation(
                descriptor,
                new AsyncCallback() {
            public void onSuccess(Object result) {
                GWT.log( result.toString(), null );
                ignoreHistory = true;
                displayConversation( descriptor );
            }
            
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }
    
    private void displayConversation( final ConversationDescriptor descriptor ){
        RootPanel p = RootPanel.get();
        p.remove(this.lobby);
        if( conversation != null ){
            p.remove( conversation );
        }
        conversation = new ConversationPanel(descriptor, comet);
        currentConversation = descriptor;
        comet.addCometListener( descriptor.name,  new CometListener(){
            public void onEvent( CometEvent evt ){
                History.newItem( descriptor.id+"|"+evt.time);
            }
        });
        
        RootPanel.get().add( conversation );
    }
    
    public void leaveConversation(final ConversationDescriptor descriptor) {
        service.leaveConversation(
                descriptor,
                new AsyncCallback() {
            public void onSuccess(Object result) {
                RootPanel.get().remove(conversation);
                conversation = null;
                comet.clearCometListeners( descriptor.name );
                ignoreHistory = false;
                selectConversation();
            }
            
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }
    
    public void listConversations(AsyncCallback callback) {
        service.listConversations(callback);
    }
    
    public void login() {
        final DialogBox dialog = new DialogBox();
        dialog.setText("Login");
        
        VerticalPanel panel = new VerticalPanel();
        dialog.setWidget(panel);
        
        final Label label = new Label("Enter Username");
        panel.add(label);
        
        final TextBox text = new TextBox();
        panel.add(text);
        
        Button button = new Button(
                "Login",
                new ClickListener() {
            public void onClick(Widget sender) {
                initComet(text.getText().trim());
                dialog.hide();
                selectConversation();
            }
        });
        panel.add( button );
        dialog.show();
        
        
    }
    
    private void initComet(String username){
        comet = comet == null ? new Comet(username) : comet;
    }
    
    public void playback( long conversationId, long startPosition ){
        initComet( "playback-quest");
        
        service.playback( conversationId, startPosition, new AsyncCallback(){
            public void onSuccess(Object result) {
                displayConversation( (ConversationDescriptor) result );
            }
            
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }
    
    public void selectConversation() {
        this.lobby = (this.lobby == null) ? new LobbyPanel() : lobby;
        RootPanel.get().add(lobby);
    }
    
    public void sendChatMessage(ConversationDescriptor descriptor, String text) {
        service.sendChatMessage(
                descriptor, text,
                new AsyncCallback() {
            public void onSuccess(Object result) {
                // do nothing.
            }
            
            public void onFailure(Throwable caught) {
                Window.alert("Failed to send message: " + caught.toString());
            }
        });
    }
}
