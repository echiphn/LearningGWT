package gwtip.sotu.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import gwtip.sotu.client.Controller;
import gwtip.sotu.client.remote.ConversationDescriptor;


/**
 *
 * @author rcooper
 */
public class CreatePanel extends VerticalPanel {
    private PasswordTextBox adminPassword = new PasswordTextBox();
    private PasswordTextBox adminPasswordConfirm = new PasswordTextBox();
    private PasswordTextBox joinPassword = new PasswordTextBox();
    private PasswordTextBox joinPasswordConfirm = new PasswordTextBox();
    private TextBox name = new TextBox();

    /** Creates a new instance of CreatePanel */
    public CreatePanel() {
        super();
        this.add(new HTML("<h2>Create New Conversation</h2>"));
        this.add(new Label("Name:"));
        this.add(name);
        this.add(new Label("Admin Password:"));
        this.add(adminPassword);
        this.add(new Label("Confirm:"));
        this.add(adminPasswordConfirm);
        this.add(new Label("Join Password:"));
        this.add(joinPassword);
        this.add(new Label("Confirm:"));
        this.add(joinPasswordConfirm);
        this.add(
            new Button(
                "Create",
                new ClickListener() {
                public void onClick(Widget sender) {
                    if (!joinPassword.getText().equals(joinPasswordConfirm.getText())) {
                        Window.alert("Join passwords do not match");

                        return;
                    }

                    if (!adminPassword.getText().equals(adminPasswordConfirm.getText())) {
                        Window.alert("Admin passwords do not match");

                        return;
                    }

                    Controller c = Controller.getInstance();
                    ConversationDescriptor d = new ConversationDescriptor();
                    d.name = name.getText();
                    d.adminPassword = adminPassword.getText();

                    if (joinPassword.getText().length() > 0) {
                        d.joinPassword = joinPassword.getText();
                    }

                    c.createConversation(d);
                }
            }));
    }
}
