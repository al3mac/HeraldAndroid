package com.szwagry.heroldandroid.preferences;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by ragnar on 10/3/15.
 */
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Preferences {

    // The field name will have default value "John"
    @DefaultString("ohmygodnovalue:(")
    String salt();

    String username();

    String token();

}