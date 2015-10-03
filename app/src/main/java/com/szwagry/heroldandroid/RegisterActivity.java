package com.szwagry.heroldandroid;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.szwagry.heroldandroid.helpers.Sha256Helper;
import com.szwagry.heroldandroid.http.HeraldRestService;
import com.szwagry.heroldandroid.http.messages.RegisterRequest;
import com.szwagry.heroldandroid.http.messages.RegisterResponse;
import com.szwagry.heroldandroid.preferences.Preferences_;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends Activity {

    ProgressDialog progressDialog;

    @Pref
    Preferences_ preferences;

    @ViewById
    EditText registerName;

    @ViewById
    EditText registerPassword;

    @RestService
    HeraldRestService heraldRestService;

    @Click({R.id.registerOkButton})
    void proceedRegistration() {
        if (validateInput(registerName) || validateInput(registerPassword)) {
            showToast("One of fields is empty!");

        } else {
            publishProgress(true);
            registerProcess(registerName, registerPassword);
        }
    }

    @Background
    void registerProcess(EditText login, EditText password) {

        String salt = Sha256Helper.getSalt();
        String hash = Sha256Helper.getHash(password.getText().toString() + salt);
        String loginName = login.getText().toString();

        RegisterRequest request = new RegisterRequest(loginName, hash);
        RegisterResponse result = heraldRestService.registerUser(request);

        if(result.getUsername()!=null) {
            // if registration ok
            preferences.salt().put(salt);
            preferences.username().put(loginName);
            publishProgress(false);
            registrationDone();
        } else {
            //registration failed
            publishProgress(false);
            login.setText("");
            password.setText("");
        }


    }

    @UiThread
    void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Click({R.id.registerCancelButton})
    void cancelRegistration() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
        startActivity(intent);
    }

    @UiThread
    void publishProgress(boolean progress) {
        if (progress) {
            progressDialog = ProgressDialog.show(this, "Logging",
                    "Processing please wait...", true);
        } else {
            progressDialog.dismiss();
        }
    }

    @UiThread
    void registrationDone() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
        startActivity(intent);
    }

    boolean validateInput(EditText text) {
        return isBlank(text.getText().toString().trim());
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
