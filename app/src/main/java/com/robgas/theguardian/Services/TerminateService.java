package com.robgas.theguardian.Services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.robgas.theguardian.SoloLearnApplication;

public class TerminateService extends android.app.Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        JobScheduler jobService = (JobScheduler) SoloLearnApplication.getApplicationInstance().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName component = new ComponentName(SoloLearnApplication.getApplicationInstance(), BackgroundCheckerScheduler.class);
        JobInfo job = new JobInfo.Builder(1, component)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        jobService.schedule(job);
        stopSelf();
    }
}
