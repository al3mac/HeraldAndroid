package com.szwagry.heroldandroid.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ragnar on 10/3/15.
 */

@EViewGroup(R.layout.navigation_item_view)
public class NavigationItemView extends LinearLayout {

    @ViewById
    TextView navigationItemName;

    public NavigationItemView(Context context) {
        super(context);
    }

    public void bind(NavigationItem item) {
        navigationItemName.setText(item.getName());
    }
}