package com.mhmdawad.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import com.mhmdawad.services.services.HeartIntentService;
import com.mhmdawad.services.services.ReminderTasks;

public class HeartNotification {

    private static final int NOTIFICATION_REQUEST_CODE = 1997;
    private static final String NOTIFICATION_CHANNEL_ID = "notificationChannel";
    private static final int NOTIFICATION_ID = 2001;
    private static final int IGNORE_NOTIFICATION_ID = 2999;
    private static final int ACCEPT_NOTIFICATION_ID = 38199;


    public static void remindUserWithNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "HEART NOTIFY",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setSmallIcon(R.drawable.ic_red_heart_24px)
                        .setLargeIcon(largeIcon(context))
                        .setContentTitle(context.getString(R.string.notificationTitle))
                        .setContentText(context.getString(R.string.notificationBody))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.notificationBody)))
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setContentIntent(contentIntent(context))
                        .addAction(ignoreNotification(context))
                        .addAction(AcceptNotification(context))
                        .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public static void clearAllNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private static NotificationCompat.Action ignoreNotification(Context context) {
        Intent intent = new Intent(context, HeartIntentService.class);
        intent.setAction(ReminderTasks.BROKEN_HEART);

        PendingIntent ignoreIntent = PendingIntent.getService(
                context,
                IGNORE_NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        return new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                "I Don't!",
                ignoreIntent);
    }

    private static NotificationCompat.Action AcceptNotification(Context context) {
        Intent intent = new Intent(context, HeartIntentService.class);
        intent.setAction(ReminderTasks.RED_HEART);

        PendingIntent ignoreIntent = PendingIntent.getService(
                context,
                ACCEPT_NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        return new NotificationCompat.Action(R.drawable.ic_red_heart_24px,
                "I Love You",
                ignoreIntent);
    }

    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(
                context,
                NOTIFICATION_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource(res, R.drawable.ic_red_heart_24px);
    }
}
