package com.szwagry.heroldandroid.http.messages;

import com.szwagry.heroldandroid.http.User;

/**
 * Invoke register request
 * @author wojciechrauner
 */
public class RegisterRequest {
    private String username;
    private String pass;

    public RegisterRequest(User user) {
        this.username = user.getLogin();
        this.pass = user.getPass();
    }

    public RegisterRequest(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
