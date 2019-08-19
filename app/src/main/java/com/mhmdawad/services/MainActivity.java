package com.mhmdawad.services;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mhmdawad.services.services.HeartIntentService;
import com.mhmdawad.services.services.ReminderTasks;

import static com.mhmdawad.services.HeartPreference.HEART_BLACK;
import static com.mhmdawad.services.HeartPreference.HEART_RED;

public class MainActivity extends AppCompatActivity
implements SharedPreferences.OnSharedPreferenceChangeListener {

    ImageButton heartImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupHeartImageButton();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        DispatcherUtilities.scheduleNotification(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }


    private void setupHeartImageButton(){

        heartImageButton = findViewById(R.id.heart);

        final Intent heartIntent = new Intent(this, HeartIntentService.class);
        heartIntent.setAction(ReminderTasks.HEART);

        editHeartColor();

        heartImageButton.setOnClickListener(new View.OnClickListener() {
            Toast toast;
            @Override
            public void onClick(View v) {
                if(toast != null) toast.cancel();
                if (HeartPreference.getHeartColor(MainActivity.this) == HEART_BLACK) {
                    heartImageButton.setImageResource(R.drawable.ic_red_heart);
                    toast = Toast.makeText(MainActivity.this, "I Love u", Toast.LENGTH_SHORT);
                    HeartNotification.remindUserWithNotification(MainActivity.this);
                } else if (HeartPreference.getHeartColor(MainActivity.this) == HEART_RED) {
                    heartImageButton.setImageResource(R.drawable.ic_black_heart);
                    toast = Toast.makeText(MainActivity.this, "Don't?", Toast.LENGTH_SHORT);
                }
                toast.show();
                startService(heartIntent);
            }
        });
    }

    private void editHeartColor(){
        switch (HeartPreference.getHeartColor(this)) {
            case HEART_BLACK:
                heartImageButton.setImageResource(R.drawable.ic_black_heart);
                break;
            case HEART_RED:
                heartImageButton.setImageResource(R.drawable.ic_red_heart);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("heart")){
            editHeartColor();
        }
    }
}

