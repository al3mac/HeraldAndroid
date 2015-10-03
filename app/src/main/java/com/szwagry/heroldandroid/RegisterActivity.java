package com.szwagry.heroldandroid;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends Activity {

    @UiThread
    @Click({R.id.registerOkButton})
    void proceedRegistration() {
        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
    }


    @Click({R.id.registerCancelButton})
    void cancelRegistration() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
        startActivity(intent);
    }
}
