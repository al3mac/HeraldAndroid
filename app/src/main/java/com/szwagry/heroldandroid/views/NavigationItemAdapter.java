package com.szwagry.heroldandroid.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragnar on 10/3/15.
 */

@EBean
public class NavigationItemAdapter extends BaseAdapter {

    List<NavigationItem> navigationItems;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        navigationItems = new ArrayList<NavigationItem>();
        navigationItems.add(new NavigationItem("Item panel"));
        navigationItems.add(new NavigationItem("Add Item"));
        navigationItems.add(new NavigationItem("Message panel"));
        navigationItems.add(new NavigationItem("Message archive"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NavigationItemView navigationItemView;
        if (convertView == null) {
            navigationItemView = NavigationItemView_.build(context);
        } else {
            navigationItemView = (NavigationItemView) convertView;
        }

        navigationItemView.bind(getItem(position));

        return navigationItemView;
    }

    @Override
    public int getCount() {
        return navigationItems.size();
    }

    @Override
    public NavigationItem getItem(int position) {
        return navigationItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}