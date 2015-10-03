package com.szwagry.heroldandroid.views;

/**
 * Created by ragnar on 10/4/15.
 */
public class SpinnerItem {
    String name;
    int src;

    public SpinnerItem(String name, int src) {
        this.name = name;
        this.src = src;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
