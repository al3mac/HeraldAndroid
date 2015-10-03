package com.szwagry.heroldandroid.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.szwagry.heroldandroid.R;
import com.szwagry.heroldandroid.http.HeraldRestService;
import com.szwagry.heroldandroid.http.messages.SaveTokenRequest;
import com.szwagry.heroldandroid.http.messages.SaveTokenResponse;
import com.szwagry.heroldandroid.preferences.Preferences_;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;

/**
 * @author wojciechrauner
 */
@EService
public class RegistrationIntentService extends IntentService {

    @Pref
    Preferences_ preferences;

    @RestService
    HeraldRestService heraldRestService;

    private static final String[] TOPICS = {"global"};

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try {
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "GCM Registration Token: " + token);
            sendRegistrationToServer(token);
            subscribeTopics(token);
        } catch (IOException e) {
            Log.e(TAG, "Canont register for GCM", e);
        }
    }

    private void sendRegistrationToServer(String token) {
        heraldRestService.setHeader("Authorization", "Bearer "+preferences.token().get());
        SaveTokenResponse saveTokenResponse = heraldRestService.saveToken(new SaveTokenRequest(token));

    }

    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
