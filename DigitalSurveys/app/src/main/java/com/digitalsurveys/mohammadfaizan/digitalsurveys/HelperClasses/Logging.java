package com.digitalsurveys.mohammadfaizan.digitalsurveys.HelperClasses;

import android.util.Log;

/**
 * Created by mohammad.faizan on 2/29/2016.
 */
public class Logging {

    private String TAG = "DIGITAL SURVEY TESTING";
    private boolean logsCheck;

    public Logging(boolean logsCheck) {
        this.logsCheck = logsCheck;
    }

    public void log(String stringToDisplay){
        try {
            if (logsCheck == true) {
                Log.i(TAG, stringToDisplay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
