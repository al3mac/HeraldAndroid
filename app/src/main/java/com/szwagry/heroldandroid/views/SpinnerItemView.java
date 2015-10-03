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
public class SpinnerItemView extends LinearLayout {

    @ViewById
    TextView textView;

    @ViewById
    ImageView imageView;

    public SpinnerItemView(Context context) {
        super(context);
    }

    public void bind(SpinnerItem item) {
        textView.setText(item.getName());
        imageView.setImageResource(item.getSrc());
    }
}