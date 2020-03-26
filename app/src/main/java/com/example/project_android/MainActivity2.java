package com.example.project_android;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;
import java.util.Timer;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences pref;
    private static final String TAG = MainActivity2.class.getSimpleName();
    private Timer timer = null;
    public static final long INTERVAL = 3000;
    private Handler mHandler = new Handler();
    RecyclerView recyclerView;

    String s1[];
    String s2[];

    int images[] = {R.drawable.past_one, R.drawable.past_two, R.drawable.past_one, R.drawable.past_two};

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        s1 = getResources().getStringArray(R.array.movies_list);
        s2 = getResources().getStringArray(R.array.movies_description);
        recyclerView = findViewById(R.id.recycleView);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("KEY1", "Test Shared Preferences");
        editor.apply();
        Log.i("Test SHared Preferences", Objects.requireNonNull(pref.getString("KEY1", null)));
        editor.remove("KEY1");
        editor.commit();

    }

    public void loadFragment1(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment1 = fragmentManager.findFragmentByTag("fragOne");

        if (fragment1 == null) {
            fragment1 = new Fragment1();
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFrag, fragment1, "fragOne");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadFragment2(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment2 = fragmentManager.findFragmentByTag("fragTwo");

        if (fragment2 == null) {
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
