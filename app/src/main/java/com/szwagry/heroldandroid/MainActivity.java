package com.szwagry.heroldandroid;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.szwagry.heroldandroid.mainActivityFragments.AddItemFragment_;
import com.szwagry.heroldandroid.mainActivityFragments.ItemPanelFragment_;
import com.szwagry.heroldandroid.mainActivityFragments.MessageArchiveFragment_;
import com.szwagry.heroldandroid.mainActivityFragments.MessagePanelFragment_;
import com.szwagry.heroldandroid.views.NavigationItem;
import com.szwagry.heroldandroid.views.NavigationItemAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    ItemPanelFragment_ itemPanelFragment = new ItemPanelFragment_();
    MessagePanelFragment_ messagePanelFragment = new MessagePanelFragment_();
    MessageArchiveFragment_ messageArchiveFragment = new MessageArchiveFragment_();
    AddItemFragment_ addItemFragment = new AddItemFragment_();

    Fragment updateFragment;
    @ViewById
    DrawerLayout drawerLayout;
    @ViewById
    ActionBarDrawerToggle drawerToggle;
    @ViewById
    DrawerArrowDrawable drawerArrow;
    boolean drawerArrowColor;

    @ViewById
    ListView navigationList;

    @Bean
    NavigationItemAdapter adapter;

    @AfterViews
    void bindAdapter() {
        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


        navigationList.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, itemPanelFragment).commit();
    }

    @UiThread
    @ItemClick({R.id.navigationList})
    void NavigationItemListClicked(NavigationItem item) {

        switch (item.getName()) {
            case "Item panel":
                updateFragment = itemPanelFragment;
                break;

            case "Message panel":
                updateFragment = messagePanelFragment;
                break;

            case "Message archive":
                updateFragment = messageArchiveFragment;
                break;

            case "Add Item":
                updateFragment = addItemFragment;
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, updateFragment).commit();
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
