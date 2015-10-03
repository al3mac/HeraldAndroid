package com.szwagry.heroldandroid;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.widget.EditText;
import android.widget.Toast;

import com.szwagry.heroldandroid.helpers.Sha256Helper;
import com.szwagry.heroldandroid.http.HeraldRestService;
import com.szwagry.heroldandroid.http.messages.LoginRequest;
import com.szwagry.heroldandroid.http.messages.LoginResponse;
import com.szwagry.heroldandroid.preferences.Preferences_;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    ProgressDialog progressDialog;

    @Pref
    Preferences_ preferences;

    @RestService
    HeraldRestService heraldRestService;

    @ViewById
    EditText loginName;

    @ViewById
    EditText loginPassword;


    @Click({R.id.loginButton})
    void onLoginButtonClick() {

        if (validateInput(loginName) || validateInput(loginPassword)) {
            showToast("One of fields is empty!");

        } else {
            publishProgress(true);
            loginProcess(loginName, loginPassword, preferences.salt().get());
        }

    }

    @Click({R.id.loginRegisterButton})
    void onRegisterButtonClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
        startActivity(intent);
    }

    @Background
    void loginProcess(EditText login, EditText password, String salt) {

        String hash = Sha256Helper.getHash(password.getText().toString()+salt);

        LoginRequest loginRequest = new LoginRequest(login.getText().toString(), hash);
        LoginResponse loginResponse = heraldRestService.loginUser(loginRequest);

        if(loginResponse.getToken()!=null) {
            preferences.token().put(loginResponse.getToken());
            preferences.username().put(login.getText().toString());
            loginDone();
        } else {
            //login unsuccessful, do sth here
        }
        publishProgress(false);
    }

    boolean validateInput(EditText text) {
        return isBlank(text.getText().toString().trim());
    }

    @UiThread
    void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
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
    void loginDone() {
        Intent intent = new Intent(LoginActivity.this, MainActivity_.class);
        startActivity(intent);
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
