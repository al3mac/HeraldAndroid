package com.szwagry.heroldandroid;


import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import com.szwagry.heroldandroid.mainActivityFragments.ItemPanelFragment_;
import com.szwagry.heroldandroid.mainActivityFragments.MessageArchiveFragment_;
import com.szwagry.heroldandroid.mainActivityFragments.MessagePanelFragment_;
import com.szwagry.heroldandroid.views.NavigationItem;
import com.szwagry.heroldandroid.views.NavigationItemAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    ItemPanelFragment_ itemPanelFragment= new ItemPanelFragment_();
    MessagePanelFragment_ messagePanelFragment= new MessagePanelFragment_();
    MessageArchiveFragment_ messageArchiveFragment= new MessageArchiveFragment_();

    Fragment updateFragment;
    @ViewById
    DrawerLayout drawerLayout;

    @ViewById
    ListView navigationList;

    @Bean
    NavigationItemAdapter adapter;

    @AfterViews
    void bindAdapter() {
        navigationList.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, itemPanelFragment).commit();
    }

    @UiThread
    @ItemClick({R.id.navigationList})
    void NavigationItemListClicked(NavigationItem item) {
        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();

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
        }

        drawerLayout.closeDrawer(Gravity.LEFT);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, updateFragment).commit();
    }
}
