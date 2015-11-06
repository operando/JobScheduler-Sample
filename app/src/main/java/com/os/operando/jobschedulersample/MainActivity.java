package com.os.operando.jobschedulersample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.os.operando.jobschedulersample.services.MyJobService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int jobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.schedule_jobs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName serviceName = new ComponentName(MainActivity.this, MyJobService.class);
                JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                for (int i = 0; i < 6; i++) {
                    PersistableBundle persistableBundle = new PersistableBundle();
                    persistableBundle.putInt("id", i);
                    JobInfo jobInfo = new JobInfo.Builder(i, serviceName)
//                            .setMinimumLatency(3000)
//                            .setOverrideDeadline(60000)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                            .setPeriodic(1000)
//                            .setRequiresCharging(true)
//                            .setPersisted(true)
//                            .setRequiresDeviceIdle(true)
                            .setExtras(persistableBundle)
                            .build();
                    scheduler.schedule(jobInfo);
                    Log.d(Tags.JOB_SCHEDULER, "Set Job.Job id = " + i);
                }
            }
        });

        findViewById(R.id.schedule_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName serviceName = new ComponentName(MainActivity.this, MyJobService.class);
                JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                PersistableBundle persistableBundle = new PersistableBundle();
                persistableBundle.putInt("id", jobId);
                JobInfo jobInfo = new JobInfo.Builder(jobId, serviceName)
//                        .setMinimumLatency(3000)
//                        .setOverrideDeadline(60000)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                            .setPeriodic(1000)
//                            .setRequiresCharging(true)
//                            .setPersisted(true)
//                            .setRequiresDeviceIdle(true)
                        .setExtras(persistableBundle)
                        .build();
                scheduler.schedule(jobInfo);
                Log.d(Tags.JOB_SCHEDULER, "Set Job.Job id = " + jobId);
                jobId++;
            }
        });

        findViewById(R.id.get_jobs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                List<JobInfo> l = scheduler.getAllPendingJobs();
                for (JobInfo ji : l) {
                    Log.d(Tags.JOB_SCHEDULER, ji.toString());
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.cancelAll();
    }
}

