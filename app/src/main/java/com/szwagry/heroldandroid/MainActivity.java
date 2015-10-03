package com.szwagry.heroldandroid;

import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.szwagry.heroldandroid.views.NavigationItem;
import com.szwagry.heroldandroid.views.NavigationItemAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

//    @ViewById
//    DrawerLayout drawerLayout;

    @ViewById
    ListView navigationList;

    @Bean
    NavigationItemAdapter adapter;

    @AfterViews
    void bindAdapter() {
        navigationList.setAdapter(adapter);
    }

    @UiThread
    @ItemClick({R.id.navigationList})
    void NavigationItemListClicked(NavigationItem item) {
        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
    }
}
