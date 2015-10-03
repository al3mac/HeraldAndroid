package com.szwagry.heroldandroid.views;

import com.szwagry.heroldandroid.http.messages.ThingResponse;

/**
 * Created by ragnar on 10/3/15.
 */
public class OwnedItem {
    private String id;
    private String name;
    private String type;
    private String addedDate;

    public OwnedItem(String id, String name, String type, String addedDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.addedDate = addedDate;
    }

    public OwnedItem(ThingResponse response) {
        this.id = response.getId();
        this.name = response.getName();
        this.type = response.getType();
        this.addedDate = response.getAddedDate();
    }

    public OwnedItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddedDate() {

        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
}
