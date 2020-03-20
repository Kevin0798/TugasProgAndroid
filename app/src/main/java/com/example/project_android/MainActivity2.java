package com.example.project_android;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences pref;
    private static final String TAG = MainActivity2.class.getSimpleName();
    private Timer timer = null;
    public static final long INTERVAL = 3000;
    private Handler mHandler = new Handler();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("KEY1", "Test Shared Preferences");
        editor.apply();

        Log.i("Test SHared Preferences", Objects.requireNonNull(pref.getString("KEY1", null)));
        editor.remove("KEY1");
        editor.commit();

    }

    public void loadFragment1 (View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("fragOne");

        if (fragment == null){
            fragment = new Fragment1();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment, "fragOne");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadFragment2(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment2 = fragmentManager.findFragmentByTag("fragOne");

        if (fragment2 == null){
            fragment2 = new Fragment2();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment2, "fragTwo");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

/*    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View view){
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler =(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Scheduled");
        }else{
            Log.d(TAG, "Job Scheduling failed");
        }
        if (timer != null){
            timer.cancel();
        }else{
            timer = new Timer();

            timer.scheduleAtFixedRate(new DisplayTimerToast(), 0, INTERVAL);
        }
    }

    private class DisplayTimerToast extends TimerTask{

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "3 second", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View view){
        JobScheduler scheduler =(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job Cancelled");
        
    }*/
}
