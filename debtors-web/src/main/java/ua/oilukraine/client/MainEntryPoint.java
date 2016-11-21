/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.oilukraine.shared.LoginInfo;

/**
 * Main entry point.
 *
 * @author igor
 */
public class MainEntryPoint implements EntryPoint {

    static final Logger logger = Logger.getLogger("NameOfYourLogger");
    private LoginInfo loginInfo = null;
//    private VerticalPanel loginPanel = new VerticalPanel();
//    private Label loginLabel = new Label(
//            "Please sign in to your Google Account to access the NoteWatcher application.");
//    private Anchor signInLink = new Anchor("Sign In");
//    private Anchor signOutLink = new Anchor("Sign Out");

    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
//    public void onModuleLoad() {
//        final Label label = new Label("Hello, GWT!!!");
//        final Button button = new Button("Click me!");
//        
//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                label.setVisible(!label.isVisible());
//            }
//        });
//        
//       
//    }
    @Override
    public void onModuleLoad() {

        loadLogin();
    }

    public void CallBackFun(String name, String pass) {
        logger.log(Level.SEVERE, "CallBackFun ++++++++++++");
        // (1) Check login status using login service.
        LoginServiceAsync loginService = GWT.create(LoginService.class);
        // (2) Create an asynchronous callback to handle the result.
        AsyncCallback acb = new AsyncCallback<LoginInfo>() {
            @Override
            public void onFailure(Throwable error) {
            }

            @Override
            public void onSuccess(LoginInfo result) {
                logger.log(Level.SEVERE, "onSuccess ++++++++++++  " + result.isLoggedIn() + " __ " + result.getName());
                if (result.isLoggedIn()) {
                    BuildMAinWindow();
                    loginInfo = result;
                } else {
                    loadLogin();
                }
            }
        };
        // (3) Make the call. Control flow will continue immediately and later
        // 'callback' will be invoked when the RPC completes.
        loginService.LogIn(name, pass, acb); //.login(GWT.getHostPageBaseURL(), acb);
    }

    private void loadLogin() {
        // Assemble login panel.
        Window.setTitle("Вход в систему");

        LoginForm lf = new LoginForm();
        lf.mep = this;
        RootPanel.get().add(lf);
    }

    void BuildMAinWindow() {
        logger.log(Level.SEVERE, "BuildMAinWindow ++++++++++++  ");

        VerticalPanel vPanel = new VerticalPanel();
        vPanel.setSpacing(10);
//        vPanel.add(new HeaderWidget().Load());
        HeaderWidget hw = new HeaderWidget();
        hw.Load();
        vPanel.add(hw);
        TabelView tw = new TabelView();
        hw.addHeaderWidgetHandler(tw);
        vPanel.add(tw);
//        CellTableExample ct = new CellTableExample();
        //        // ct.onModuleLoad();
        NewUiBinder nb = new NewUiBinder("able", "baker", "charlie");
//        RootPanel.get().add(button);
//        RootPanel.get().add(label);
        vPanel.add(nb);
        RootPanel.get().add(vPanel);
//        RootPanel.get().add(ct);
        Window.setTitle("Ввод дебиторской задолженности");

    }
}
