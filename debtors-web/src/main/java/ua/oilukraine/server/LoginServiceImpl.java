/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.oilukraine.client.LoginService;
import ua.oilukraine.shared.LoginInfo;

/**
 *
 * @author u_gorbonos
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    @Override
    public LoginInfo LogIn(String name, String pass) {
        Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, "LoginServiceImpl:LogIn");
        System.out.println("LoginServiceImpl:LogIn");
        DB db = new DB();
        int code = db.logIn(name, pass);
        LoginInfo li = new LoginInfo(name, pass);
            if (-1 != code) {
            li.setLoggedIn(true);
        }
        return li;
    }
}
