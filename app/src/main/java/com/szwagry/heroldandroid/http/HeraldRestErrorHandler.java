package com.szwagry.heroldandroid.http;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

/**
 * Created by ragnar on 10/3/15.
 */

@EBean
public class HeraldRestErrorHandler implements RestErrorHandler {

    @RootContext
    Context context;

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        String errorMessage = e.getMessage().toString();
        showToast(errorMessage);
    }

    @UiThread
    void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
