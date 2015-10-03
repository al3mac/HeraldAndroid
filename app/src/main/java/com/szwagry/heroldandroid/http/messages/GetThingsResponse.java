package com.szwagry.heroldandroid.http.messages;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wojciechrauner
 */
public class GetThingsResponse {
    private List<String> things = new ArrayList<>();

    public List<String> getThings() {
        return things;
    }

    public void setThings(List<String> things) {
        this.things = things;
    }
}
