package com.szwagry.heroldandroid.mainActivityFragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.szwagry.heroldandroid.R;
import com.szwagry.heroldandroid.views.OwnerItemAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_item_panel)
public class ItemPanelFragment extends Fragment {

    @ViewById
    ListView ownedItemList;

    @Bean
    OwnerItemAdapter adapter;

    @AfterViews
    void bindAdapter() {

        ownedItemList.setAdapter(adapter);
        ownedItemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, final int pos, long id) {

                new AlertDialog.Builder(ItemPanelFragment.this.getActivity())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteItemFromAdapter(pos);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            }
        });

    }


    @UiThread
    void deleteItemFromAdapter(int pos) {
        adapter.getOwnerItemList().remove(pos);
        adapter.notifyDataSetChanged();
    }
}
