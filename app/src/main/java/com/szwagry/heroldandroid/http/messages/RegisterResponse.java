package com.szwagry.heroldandroid.http.messages;

/**
 * Server response to the register request
 * @author wojciechrauner
 */
public class RegisterResponse {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
