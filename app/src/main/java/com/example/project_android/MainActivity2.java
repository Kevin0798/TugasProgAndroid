package com.example.project_android;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences pref;
    public PagesAdapter pagesAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem home;
    private TabItem movies;
    private TabItem chat;
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

        /*recyclerView = findViewById(R.id.recycleView);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);*/
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        home = (TabItem) findViewById(R.id.homeList);
        movies = (TabItem) findViewById(R.id.movieList);
        chat = (TabItem) findViewById(R.id.chatList);
        viewPager = findViewById(R.id.viewpager);

        pagesAdapter = new PagesAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagesAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0) {
                    pagesAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 1) {
                    pagesAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 2){
                    pagesAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        recyclerView = null;
        s1 = getResources().getStringArray(R.array.movies);
        s2 = getResources().getStringArray(R.array.m_description);

        /*SharedPreferences.Editor editor = pref.edit();
        editor.putString("KEY1", "Test Shared Preferences");
        editor.apply();
        Log.i("Test SHared Preferences", Objects.requireNonNull(pref.getString("KEY1", null)));
        editor.remove("KEY1");
        editor.commit();*/

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
