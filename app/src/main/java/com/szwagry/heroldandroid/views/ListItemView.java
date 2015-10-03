package com.szwagry.heroldandroid.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ragnar on 10/3/15.
 */

@EViewGroup(R.layout.list_row)
public class ListItemView extends LinearLayout {

    @ViewById
    TextView textView;

    @ViewById
    ImageView imageView;

    public ListItemView(Context context) {
        super(context);
    }

    public void bind(ListItem item) {
        textView.setText(item.getName());
        imageView.setImageResource(item.getSrc());
    }
}