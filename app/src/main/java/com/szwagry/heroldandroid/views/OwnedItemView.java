package com.szwagry.heroldandroid.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ragnar on 10/3/15.
 */

@EViewGroup(R.layout.owned_item_view)
public class OwnedItemView extends LinearLayout {

    @ViewById
    ImageView ownedItemType;

    @ViewById
    TextView ownedItemName;

    @ViewById
    TextView ownedItemDate;

    public OwnedItemView(Context context) {
        super(context);
    }

    public void bind(OwnedItem item) {
        ownedItemName.setText(item.getName());
//        setImageView(item.getType());
        ownedItemDate.setText(item.getAddedDate());
    }

    private void setImageView(String type) {
        String src = "";
        switch (type) {
            case "Bicycle":
                src = "";
                break;
            case "Car":
                src = "";
                break;
            case "Mobile":
                src = "";
                break;
        }
        ownedItemType.setImageDrawable(Drawable.createFromPath(src));
    }
}