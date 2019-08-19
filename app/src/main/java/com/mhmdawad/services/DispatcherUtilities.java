package com.mhmdawad.services;

import android.content.Context;
import android.support.annotation.NonNull;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import java.util.concurrent.TimeUnit;

 class DispatcherUtilities {

    private static final int MINUTES = (int) TimeUnit.MINUTES.toSeconds(1);
    private static final String JOB_TAG = "heart-job-tags";
    private static boolean sInc = false;


    synchronized static void scheduleNotification(@NonNull final Context context) {
        if(sInc) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job myJob = dispatcher.newJobBuilder()
                .setService(HearJobDispatcher.class)
                .setTag(JOB_TAG)
                .setRecurring(true)
                .setLifetime(Lifetime.FOREVER)
                .setTrigger(Trigger.executionWindow(MINUTES, MINUTES+MINUTES))
                .setReplaceCurrent(true)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .build();

        dispatcher.schedule(myJob);
        sInc = true;
    }

}
