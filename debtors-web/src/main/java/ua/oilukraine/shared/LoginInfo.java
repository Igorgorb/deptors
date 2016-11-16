/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
//import java.io.Serializable;

public class LoginInfo implements IsSerializable {

    private boolean loggedIn = false;
    private String loginUrl;
    private String logoutUrl;
    private String emailAddress;
    private String nickname;
    private String name;
    private String pass;

    public LoginInfo() {
        this.setName("Anonimous");
        this.setPass("anonimous@anonimous.com");
    }

    public LoginInfo(String name, String pass) {
        this.setName(name);
        this.setPass(pass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        CheckIt();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        CheckIt();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
     * Проверяет правильность пользователя
     * @return 
     */
    private boolean CheckIt() {
        //this.loggedIn = "igor".equals(name) && "pass".equals(pass);
        return this.loggedIn;
    }
}
