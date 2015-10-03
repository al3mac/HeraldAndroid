package com.szwagry.heroldandroid.views;

import java.util.Date;

/**
 * Created by ragnar on 10/3/15.
 */
public class OwnedItem {
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public OwnedItem(String id, String name, String type, String addedDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.addedDate = addedDate;
    }

    public String getAddedDate() {

        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    private String addedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
