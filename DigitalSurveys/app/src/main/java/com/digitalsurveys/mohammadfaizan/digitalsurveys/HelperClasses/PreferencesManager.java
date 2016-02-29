package com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mohammad.faizan on 2/29/2016.
 */
public class PreferencesManager {

    SharedPreferences sharedPreference;
    public static final String SHAREDPREF = "SHAREDPREF";

    public PreferencesManager(Context context) {
        sharedPreference = context.getSharedPreferences(SHAREDPREF, context.MODE_PRIVATE);
    }

    public void addPreference(String key, String value){
        try {
            SharedPreferences.Editor editor = sharedPreference.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getStringPreference(String key){
        String value = null;
        try {
            value = sharedPreference.getString(key, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
