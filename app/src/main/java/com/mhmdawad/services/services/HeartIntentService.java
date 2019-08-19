package com.mhmdawad.services.services;

import android.app.IntentService;
import android.content.Intent;

public class HeartIntentService extends IntentService {
    public HeartIntentService() {
        super("HeartIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        ReminderTasks.executeTask(this,action);

    }
}
