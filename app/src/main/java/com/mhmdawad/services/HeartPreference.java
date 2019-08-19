package com.mhmdawad.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HeartPreference {

    private static final String HEART_COLOR = "heart";
    static final int HEART_BLACK = 0;
    static final int HEART_RED = 1;

    public static void setHeartColor(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        switch (getHeartColor(context)) {
            case HEART_BLACK:
                editor.putInt(HEART_COLOR, HEART_RED);
                break;
            case HEART_RED:
                editor.putInt(HEART_COLOR, HEART_BLACK);
        }

        editor.apply();

    }

    static int getHeartColor(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(HEART_COLOR, HEART_BLACK);
    }
}
