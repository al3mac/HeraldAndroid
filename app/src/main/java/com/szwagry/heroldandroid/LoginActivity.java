package com.szwagry.heroldandroid;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity{

    ProgressDialog progressDialog;

    @ViewById
    EditText loginName;

    @ViewById
    EditText loginPassword;


    @Click({R.id.loginButton})
    void onLoginButtonClick() {

        if( validateInput(loginName) || validateInput(loginPassword) ){
            showToast("One of fields is empty!");
        } else{
            publishProgress(true);
            loginProcess(loginName, loginPassword);
        }

    }

    @Click({R.id.loginRegisterButton})
    void onRegisterButtonClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
        startActivity(intent);
    }

    @Background
    void loginProcess(EditText login, EditText password) {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publishProgress(false);
    }

    boolean validateInput(EditText text){
        return isBlank(text.getText().toString().trim());
    }

    @UiThread
    void showToast (String text) {
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
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

    boolean isBlank(String str) {
                    int strLen;
                    if (str == null || (strLen = str.length()) == 0) {
                            return true;
                        }
                    if(str == ""){
                        return true;
                    }
                    return false;
                }

}
