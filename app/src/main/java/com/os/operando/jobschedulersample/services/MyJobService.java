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
        Log.d(Tags.JOB_SCHEDULER, "onStartJob Job id = " + params.getJobId());
        PersistableBundle persistableBundle = params.getExtras();
        new Thread(new TestRunnable(params)).start();
        Log.d(Tags.JOB_SCHEDULER, "Extras id = " + persistableBundle.getInt("id"));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(Tags.JOB_SCHEDULER, "onStopJob Job id = " + params.getJobId());
        return false;
    }

    private class TestRunnable implements Runnable {

        private JobParameters mParams;

        public TestRunnable(JobParameters params) {
            mParams = params;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jobFinished(mParams, false);
        }
    }
}