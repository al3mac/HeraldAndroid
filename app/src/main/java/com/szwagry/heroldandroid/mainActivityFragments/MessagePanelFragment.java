package com.szwagry.heroldandroid.mainActivityFragments;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_message_panel)
public class MessagePanelFragment extends Fragment {

    ProgressDialog progressDialog;

    String ownedItemId;

    @ViewById(R.id.messageText)
    EditText messageText;

    @Click(R.id.tagInformationButton)
    void getTagInformation(){

    }

    @Click(R.id.messageSend)
    void sendMessage(){
        String message = messageText.getText().toString();
        if(isBlank(message)){
            showToast("Message is empty!");
        } else {
            sendMessage(message);
        }
    }

    @Background
    void sendMessage(String message){
        publishProgress(true);
        processSendMessage(message);
        publishProgress(false);
    }

    private void processSendMessage(String message) {
        showToast("Message sent! "+message);
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
