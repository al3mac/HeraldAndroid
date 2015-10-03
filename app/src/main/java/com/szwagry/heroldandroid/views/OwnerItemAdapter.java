package com.szwagry.heroldandroid.views;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.szwagry.heroldandroid.http.HeraldRestService;
import com.szwagry.heroldandroid.http.messages.GetThingsRequest;
import com.szwagry.heroldandroid.http.messages.GetThingsResponse;
import com.szwagry.heroldandroid.http.messages.ThingResponse;
import com.szwagry.heroldandroid.preferences.Preferences_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ragnar on 10/3/15.
 */

@EBean
public class OwnerItemAdapter extends BaseAdapter {

    public static final String LOG_TAG = "OwnerItemAdapter";

    public List<OwnedItem> getOwnerItemList() {
        return ownerItemList;
    }

    List<OwnedItem> ownerItemList;

    @RootContext
    Context context;

    @RestService
    HeraldRestService heraldRestService;

    @Pref
    Preferences_ preferences;

    @AfterInject
    void initAdapter() {
        ownerItemList = new ArrayList<OwnedItem>();
        ownerItemList.add(new OwnedItem("1", "Rower 1", "Bicycle", "01-10-2015"));
        ownerItemList.add(new OwnedItem("2", "Rower 2", "Bicycle", "01-10-2015"));
        ownerItemList.add(new OwnedItem("3", "Samochod", "Car", "01-10-2015"));
        ownerItemList.add(new OwnedItem("4", "iPhone", "Mobile", "01-10-2015"));
        new LongOperation(this, heraldRestService, preferences).execute();
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


 class LongOperation extends AsyncTask<String, Void, String> {

    public LongOperation(OwnerItemAdapter ownerItemAdapter, HeraldRestService heraldRestService,Preferences_ preferences){
        this.ownerItemAdapter = ownerItemAdapter;
        this.heraldRestService = heraldRestService;
        this.preferences = preferences;

    }
     HeraldRestService heraldRestService;
     OwnerItemAdapter ownerItemAdapter;
     Preferences_ preferences;


    @Override
    protected String doInBackground(String... params) {
        heraldRestService.setHeader("Authorization", "Bearer " + preferences.token().get());
        GetThingsResponse response = heraldRestService.getThings();
        if(response!=null) {
            for(String thingsId : response.getThings()) {
                ThingResponse thing = heraldRestService.getThing(thingsId);
                OwnedItem ownedItem = new OwnedItem(thing);
                ownerItemAdapter.getOwnerItemList().add(ownedItem);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        ownerItemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}