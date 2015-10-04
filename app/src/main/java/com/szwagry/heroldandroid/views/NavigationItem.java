package com.szwagry.heroldandroid.views;

/**
 * Created by ragnar on 10/3/15.
 */
public class NavigationItem {
    private String name;
    private int src;

    public int getSrc() {
        return src;
    }

    public NavigationItem(String name, int src) {
        this.name = name;
        this.src = src;
    }

    public String getName() {
        return this.name;
    }
}
