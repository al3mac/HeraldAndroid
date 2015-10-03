package com.szwagry.heroldandroid.views;

import android.graphics.drawable.Drawable;

/**
 * Created by ragnar on 10/3/15.
 */
public class NavigationItem {
    private String name;
    private int resource;

    public NavigationItem(String name, int resource) {
        this.name = name;
        this.resource = resource;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
