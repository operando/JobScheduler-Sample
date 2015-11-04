package com.os.operando.jobschedulersample.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import android.util.Log;

import com.os.operando.jobschedulersample.Tags;

public class MyJobService extends JobService {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(Tags.JOB_SCHEDULER, "Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Tags.JOB_SCHEDULER, "Service destroyed");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(Tags.JOB_SCHEDULER, "Start Job id = " + params.getJobId());
        PersistableBundle persistableBundle = params.getExtras();
        Log.d(Tags.JOB_SCHEDULER, "Extras id = " + persistableBundle.getInt("id"));
        jobFinished(params, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(Tags.JOB_SCHEDULER, "End Job id = " + params.getJobId());
        return false;
    }
}