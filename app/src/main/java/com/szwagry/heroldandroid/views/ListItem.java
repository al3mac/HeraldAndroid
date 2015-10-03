package com.szwagry.heroldandroid.views;

/**
 * Created by ragnar on 10/3/15.
 */
public class ListItem {

    private String name;
    private int src;

    public ListItem(String name, int src) {
        this.src = src;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
