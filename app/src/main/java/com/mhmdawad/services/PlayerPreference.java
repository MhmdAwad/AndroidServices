package com.mhmdawad.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PlayerPreference {

    private static final String PLAYER_NAME = "playerName";
    private static final String PLAYER_POINTS = "playerPoints";

     static void setPlayerName(Context context, String name) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(PLAYER_NAME,name);
        editor.apply();
    }
     static String getPlayerName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PLAYER_NAME,"");
    }

     static int getPlayerPoints(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(PLAYER_POINTS,0);
    }

     static void setPlayerPoints(Context context, int point) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PLAYER_POINTS,point);
        editor.apply();
    }

}
