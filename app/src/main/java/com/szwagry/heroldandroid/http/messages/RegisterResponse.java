package com.szwagry.heroldandroid.http.messages;

/**
 * Server response to the register request
 * @author wojciechrauner
 */
public class RegisterResponse {
    private String id; //todo only id here, but intercept 501 codes and use other class

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
