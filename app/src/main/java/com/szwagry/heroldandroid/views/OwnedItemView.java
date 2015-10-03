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

    @ViewById
    TextView ownedItemId;

    public OwnedItemView(Context context) {
        super(context);
    }

    public void bind(OwnedItem item) {
        ownedItemName.setText(item.getName());
        setImageView(item.getType());
        ownedItemDate.setText(item.getAddedDate());
        ownedItemId.setText(item.getId());
    }

    private void setImageView(String type) {
        int src = 0 ;
        switch (type) {
            case "Bicycle":
                src = R.drawable.bicycle;
                break;
            case "Car":
                src = R.drawable.car;
                break;
            case "Mobile":
                src =R.drawable.mobile;
                break;
        }
        ownedItemType.setImageResource(src);
    }
}