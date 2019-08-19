package com.mhmdawad.services;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.mhmdawad.services.services.ReminderTasks;


public class HearJobDispatcher extends JobService {

    private AsyncTask mAsyncTask;

    @Override
    public boolean onStartJob(@NonNull final JobParameters job) {

            mAsyncTask =  new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] objects) {
            ReminderTasks.executeTask(HearJobDispatcher.this,ReminderTasks.MM);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            jobFinished(job , false);
        }
    };
        mAsyncTask.execute();

        return true;


}
    @Override
    public boolean onStopJob(@NonNull JobParameters job) {
        if (mAsyncTask != null) mAsyncTask.cancel(true);
        return true;
    }
}
