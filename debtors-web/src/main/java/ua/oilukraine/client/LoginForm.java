/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u_gorbonos
 */
public class LoginForm extends Composite {

    Logger logger = Logger.getLogger("NameOfYourLogger");
    private static LoginFormUiBinder uiBinder = GWT.create(LoginFormUiBinder.class);

    interface LoginFormUiBinder extends UiBinder<Widget, LoginForm> {
    }

    public LoginForm() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    TextBox loginBox;

    @UiField
    TextBox passwordBox;

    @UiField
    Label completionLabel1;

    @UiField
    Label completionLabel2;

    private Boolean tooShort = false;

    public MainEntryPoint mep = null;

    /*
   * Method name is not relevant, the binding is done according to the class
   * of the parameter.
     */
    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        if (!tooShort) {
            //Window.alert("Login Successful!");
            if (!(null == mep)) {
                logger.log(Level.SEVERE, "mep.CallBackFun ++++++++++++");
                mep.CallBackFun(loginBox.getText(), passwordBox.getText());
                RootPanel.get().remove(this);
            }
        } else {
            Window.alert("Login or Password is too short!");
        }
    }

    @UiHandler("loginBox")
    void handleLoginChange(ValueChangeEvent<String> event) {
        logger.log(Level.SEVERE, "handleLoginChange:loginBox ++++++++++++");
        
        if (event.getValue().length() < 6) {
            completionLabel1.setText("Login too short (Size must be > 6)");
            tooShort = true;
        } else {
            tooShort = false;
            completionLabel1.setText("");
        }
    }

    @UiHandler("passwordBox")
    void handlePasswordChange(ValueChangeEvent<String> event) {
        logger.log(Level.SEVERE, "handlePasswordChange:passwordBox ++++++++++++");
        if (event.getValue().length() < 6) {
            tooShort = true;
            completionLabel2.setText("Password too short (Size must be > 6)");
        } else {
            tooShort = false;
            completionLabel2.setText("");
        }
    }
}
