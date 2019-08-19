package com.mhmdawad.services.services;

import android.content.Context;
import com.mhmdawad.services.HeartNotification;
import com.mhmdawad.services.HeartPreference;


public class ReminderTasks {

    public static final String HEART = "myHeart";
    public static final String RED_HEART = "myHeartRed";
    public static final String BROKEN_HEART = "nope";
    public static final String MM = "heartMeem";

    public static void executeTask(Context context, String action) {
        switch (action) {
            case HEART:
                HeartPreference.setHeartColor(context);
                break;
            case BROKEN_HEART:
                HeartNotification.clearAllNotification(context);
                break;
            case RED_HEART:
                HeartPreference.setHeartColor(context);
                HeartNotification.clearAllNotification(context);
                break;
            case MM:
                HeartNotification.remindUserWithNotification(context);
        }
    }


}
