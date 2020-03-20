package com.example.project_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

import static android.text.Html.fromHtml;
import static android.widget.Toast.*;
import static com.example.project_android.MainActivity2.INTERVAL;


public class MainActivity extends AppCompatActivity {
    WifiStateChangeReceiver wifiStateChangeReceiver = new WifiStateChangeReceiver();
    private TextView EmailText;
    private  TextView PasswordText;
    private  Button Loginbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences sharedPreferences;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    private Timer timer = null;
    public static final long INTERVAL = 3000;
    private Handler mHandler = new Handler();
    private static final String TAG = MainActivity.class.getSimpleName();

    SqliteHelper sqliteHelper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        //initViews();


        EmailText = findViewById(R.id.emailText);
        PasswordText = findViewById(R.id.passwordText);
        Loginbtn = findViewById(R.id.loginButton);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        scheduleJob();
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Mendapatkan Values dari inputan EditText field
                    String Email = EmailText.getText().toString();
                    String Password = PasswordText.getText().toString();

                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    if (currentUser == null) {
                        makeText(getApplicationContext(), "Login Berhasil", LENGTH_SHORT).show();
                        openActivity();
                    }
                    else {
                        makeText(getApplicationContext(), "Login Gagal", LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void scheduleJob() {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler scheduler =(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
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

            timer.scheduleAtFixedRate(new MainActivity.DisplayTimerToast(), 0, INTERVAL);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiStateChangeReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateChangeReceiver);
    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#000000'>Not a member ?. </font><font color='#0c0099'>Sign Up</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
    }

    public boolean validate() {
        boolean valid;

        //Get values from EditText fields
        String Email = EmailText.getText().toString();
        String Password = PasswordText.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            makeText(getApplicationContext(), "Email tidak valid", LENGTH_SHORT).show();
            //textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            makeText(getApplicationContext(), "Welcome", LENGTH_SHORT).show();
            //textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            makeText(getApplicationContext(), "Password not valid", LENGTH_SHORT).show();
            //textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;

                //textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                makeText(getApplicationContext(), "Password is to short", LENGTH_SHORT).show();
                //textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }

    private class DisplayTimerToast extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    makeText(getApplicationContext(), "3 second", LENGTH_LONG).show();
                }
            });
        }
    }



    public void openActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

        /*private void initViews(){
        EmailText = (EditText) findViewById(R.id.emailText);
        PasswordText = (EditText) findViewById(R.id.passwordText);
        *//*textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);*//*

        Loginbtn = (Button) findViewById(R.id.loginButton);

    }*/

    /*@SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }*/

}
