package com.szwagry.heroldandroid.services;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

import org.androidannotations.annotations.EService;

/**
 * @author wojciechrauner
 */
@EService
public class HeraldIdListenerService extends InstanceIDListenerService {
    private static final String TAG = "InstanceIDListenerService";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService_.class);
        startService(intent);
    }
}
