package com.szwagry.heroldandroid.http.messages;

/**
 * Server response to the register request
 * @author wojciechrauner
 */
public class RegisterResponse {
    private String id;
    private String status;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
