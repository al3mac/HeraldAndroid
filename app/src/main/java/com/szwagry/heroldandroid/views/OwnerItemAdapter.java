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
public class OwnerItemAdapter extends BaseAdapter {

    public List<OwnedItem> getOwnerItemList() {
        return ownerItemList;
    }



    List<OwnedItem> ownerItemList;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        ownerItemList = new ArrayList<OwnedItem>();
        ownerItemList.add(new OwnedItem("1", "Rower 1", "Bicycle", "01-10-2015"));
        ownerItemList.add(new OwnedItem("2", "Rower 2", "Bicycle", "01-10-2015"));
        ownerItemList.add(new OwnedItem("3", "Samochod", "Car", "01-10-2015"));
        ownerItemList.add(new OwnedItem("4", "iPhone", "Mobile", "01-10-2015"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       OwnedItemView ownedItemView;
        if (convertView == null) {
            ownedItemView = OwnedItemView_.build(context);
        } else {
            ownedItemView = (OwnedItemView) convertView;
        }

        ownedItemView.bind(getItem(position));

        return ownedItemView;
    }

    @Override
    public int getCount() {
        return ownerItemList.size();
    }

    @Override
    public OwnedItem getItem(int position) {
        return ownerItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}