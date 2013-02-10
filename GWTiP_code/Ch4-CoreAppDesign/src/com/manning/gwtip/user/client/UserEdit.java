package com.manning.gwtip.user.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserEdit extends Composite{
    private StackPanel stack = new StackPanel();
    private TextBox username = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();
    private PasswordTextBox passwordConfirm = new PasswordTextBox();
    private TextBox firstName = new TextBox();
    private TextBox lastName = new TextBox();
    private TextArea notes = new TextArea();
    private User user;
    private AddressEdit billingAddress;
    private AddressEdit shippingAddress;
    
    private PropertyChangeListener[] listeners = new PropertyChangeListener[5];
    public UserEdit(final UserCreateListener controller, final User user) {
        super();
        this.user = user;
        stack.setHeight( "350px");
        VerticalPanel basePanel = new VerticalPanel();
        Button save = new Button( "Save");
        
        basePanel.add( save );
        basePanel.add( stack );
        FlexTable ft = new FlexTable();
        ft.setWidget( 0,0, new Label("Username") );
        ft.setWidget( 0,1, username );
        ft.setWidget( 1,0, new Label("Password") );
        ft.setWidget( 1,1, password );
        ft.setWidget( 2,0, new Label("Confirm") );
        ft.setWidget( 2,1, passwordConfirm );
        
        ft.setWidget( 3,0, new Label("First Name") );
        ft.setWidget( 3,1, firstName );
        ft.setWidget( 4,0, new Label( "Last Name") );
        ft.setWidget( 4,1, lastName );
        
        stack.add( ft, "User Information" );
        billingAddress = new AddressEdit( user.getBillingAddress());
        stack.add( billingAddress, "Billing Address");
        shippingAddress = new AddressEdit( user.getShippingAddress());
        stack.add( shippingAddress, "Shipping Address");
        notes.setWidth("100%");
        notes.setHeight("250px");
        stack.add( notes, "Notes" );
        
        save.addClickListener( new ClickListener(){
            public void onClick(Widget sender) {
                if( !password.getText().equals( passwordConfirm.getText() )){
                    Window.alert("Passwords do not match!");
                }
                Window.alert( user.getUsername() );
                controller.createUser( user );
            }
        });
        
        listeners[0] = new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                firstName.setText( (String) propertyChangeEvent.getNewValue() );
            }
        };
        user.addPropertyChangeListener( "firstName", listeners[0]);
        firstName.addChangeListener( new ChangeListener(){
            public void onChange(Widget sender) {
                user.setFirstName( firstName.getText() );
            }
        });
        
        listeners[1] = new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                firstName.setText( (String) propertyChangeEvent.getNewValue() );
            }
        };
        user.addPropertyChangeListener( "lastName", listeners[1]);
        lastName.addChangeListener( new ChangeListener(){
            public void onChange(Widget sender) {
                user.setLastName( lastName.getText() );
            }
        });
        
        listeners[2] = new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                username.setText( (String) propertyChangeEvent.getNewValue() );
            }
        };
        user.addPropertyChangeListener( "username", listeners[2]);
        username.addChangeListener( new ChangeListener(){
            public void onChange(Widget sender) {
                user.setUsername( username.getText() );
            }
        });
        
        listeners[3] = new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                password.setText( (String) propertyChangeEvent.getNewValue() );
            }
        };
        user.addPropertyChangeListener( "password", listeners[3]);
        password.addChangeListener( new ChangeListener(){
            public void onChange(Widget sender) {
                user.setPassword( password.getText() );
            }
        });
        
        
        this.initWidget( basePanel );
        
    }
    
    public void cleanup(){
        for( int i=0; i < listeners.length; i++){
            user.removePropertyChangeListener( listeners[i] );
        }
    }
    
}
