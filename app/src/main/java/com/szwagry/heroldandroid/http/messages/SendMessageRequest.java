package com.szwagry.heroldandroid.http.messages;

/**
 * @author wojciechrauner
 */
public class SendMessageRequest {
    private String id;
    private String message;

    public SendMessageRequest(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
