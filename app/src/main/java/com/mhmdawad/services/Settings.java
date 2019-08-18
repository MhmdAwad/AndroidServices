package com.mhmdawad.services;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingPreference())
                .commit();
    }


    public static class SettingPreference extends PreferenceFragmentCompat
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        EditTextPreference nameEditTextPreference;
        EditTextPreference pointsEditTextPreference;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preference);
            nameEditTextPreference = (EditTextPreference) findPreference("nameForPlayer");
            pointsEditTextPreference = (EditTextPreference) findPreference("pointsForPlayer");


            int points = Integer.parseInt(pointsEditTextPreference.getText());
            PlayerPreference.setPlayerPoints(getContext(), points);
            pointsEditTextPreference.setSummary(String.valueOf(PlayerPreference.getPlayerPoints(getContext())));

            String name = nameEditTextPreference.getText();
            PlayerPreference.setPlayerName(getContext(), name);
            nameEditTextPreference.setSummary(PlayerPreference.getPlayerName(getContext()));

            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference p = findPreference(key);
            if(p instanceof EditTextPreference){
                String val = sharedPreferences.getString(key,"");
                p.setSummary(val);
            }
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
    }
}



