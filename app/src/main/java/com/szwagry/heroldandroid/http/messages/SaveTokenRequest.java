package com.szwagry.heroldandroid.http.messages;

/**
 * @author wojciechrauner
 */
public class SaveTokenRequest {
    private String token;

    public SaveTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
