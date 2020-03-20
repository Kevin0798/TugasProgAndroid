package com.example.project_android;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static final String TAG = MyJobService.class.getSimpleName();
    private boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job Started");

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job Cancelled");
        jobCancelled = true;
        return true;
    }

    private void doBackground(final JobParameters params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    Log.d(TAG, "run: " + i);
                    if (jobCancelled){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        jobFinished(params, false);
                    }
                }
            }
        }).start();
    }
}
