package com.szwagry.heroldandroid.mainActivityFragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.szwagry.heroldandroid.R;
import com.szwagry.heroldandroid.http.HeraldRestService;
import com.szwagry.heroldandroid.http.messages.SendMessageRequest;
import com.szwagry.heroldandroid.http.messages.SendMessageResponse;
import com.szwagry.heroldandroid.preferences.Preferences_;
import com.szwagry.heroldandroid.views.ListItemAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;


@EFragment(R.layout.fragment_message_panel)
public class MessagePanelFragment extends Fragment {

    ProgressDialog progressDialog;

    String ownedItemId;

    @Bean
    ListItemAdapter adapter;

    @ViewById(R.id.messageText)
    EditText messageText;

    @Pref
    Preferences_ preferences;

    @RestService
    HeraldRestService heraldRestService;

    @Click(R.id.tagInformationButton)
    void getTagInformation(){

        DialogPlus dialog = DialogPlus.newDialog(MessagePanelFragment.this.getActivity())
                .setAdapter(adapter)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                        if(position == 0){
                            invokeString();
                            dialog.dismiss();
                        } else{
                            dialog.dismiss();
                        }

                    }
                })
                .setContentHolder(new ListHolder())
                .setGravity(Gravity.CENTER)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();
    }

    void invokeString(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MessagePanelFragment.this.getActivity());
        builder.setTitle("Put String code!");


        final EditText input = new EditText(MessagePanelFragment.this.getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ownedItemId = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Click(R.id.messageSend)
    void sendMessage(){
        String message = messageText.getText().toString();
        if(isBlank(message)){
            showToast("Message is empty!");
        } else {
            sendMessage(message, ownedItemId);
        }
    }

    @Background
    void sendMessage(String message, String ownedItemId){
        publishProgress(true);
        processSendMessage(message, ownedItemId);
        publishProgress(false);
        backToItemPanel();
    }

    @UiThread
    void backToItemPanel() {
        getFragmentManager().beginTransaction().replace(R.id.container, new ItemPanelFragment_()).commit();
    }

    private void processSendMessage(String message, String ownedItemId) {
        heraldRestService.setHeader("Authorization", "Bearer " + preferences.token().get());
        SendMessageResponse response = heraldRestService.sendMessage(new SendMessageRequest(ownedItemId, message));
        if(response!=null && "true".equals(response.getSend())) {
            showToast("Message sent! " + message + " to " + ownedItemId);
        } else {
            showToast("Error occurred, are you sure that id is valid?");
        }
    }

    @UiThread
    void publishProgress(boolean progress) {
        if (progress) {
            progressDialog = ProgressDialog.show(MessagePanelFragment.this.getActivity(), "Logging",
                    "Processing please wait...", true);
        } else {
            progressDialog.dismiss();
        }
    }

    @UiThread
    void showToast(String text) {
        Toast.makeText(MessagePanelFragment.this.getActivity(), text, Toast.LENGTH_LONG).show();
    }

    boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        if (str == "") {
            return true;
        }
        return false;
    }
}
