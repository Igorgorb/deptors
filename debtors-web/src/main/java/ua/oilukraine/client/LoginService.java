/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.oilukraine.shared.LoginInfo;

/**
 *
 * @author u_gorbonos
 */
@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {

    public LoginInfo LogIn(String name, String pass);
}
