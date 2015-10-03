package com.szwagry.heroldandroid.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragnar on 10/3/15.
 */

@EBean
public class ListItemAdapter extends BaseAdapter {

    List<ListItem> listItems;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        listItems = new ArrayList<ListItem>();
        listItems.add(new ListItem("String", R.drawable.s));
        listItems.add(new ListItem("QR Code",R.drawable.wr));
        listItems.add(new ListItem("NFC",R.drawable.nfc));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListItemView listItemView;
        if (convertView == null) {
            listItemView = ListItemView_.build(context);
        } else {
            listItemView = (ListItemView) convertView;
        }

        listItemView.bind(getItem(position));

        return listItemView;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public ListItem getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}