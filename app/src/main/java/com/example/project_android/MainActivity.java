package com.example.project_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import static android.text.Html.fromHtml;
import static com.example.project_android.WifiStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity extends AppCompatActivity {
    private TextView EmailText;
    private  TextView PasswordText;
    private  Button Loginbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences sharedPreferences;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    SqliteHelper sqliteHelper;

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

        IntentFilter intentFilter = new IntentFilter(WifiStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";

                Snackbar.make(findViewById(R.id.activity_main), "Network Status: " +networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);


        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Mendapatkan Values dari inputan EditText field
                    String Email = EmailText.getText().toString();
                    String Password = PasswordText.getText().toString();

                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    if (currentUser == null) {
                        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                        openActivity();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#000000'>Not a member ?. </font><font color='#0c0099'>Sign Up</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
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

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = EmailText.getText().toString();
        String Password = PasswordText.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Email tidak valid", Toast.LENGTH_SHORT).show();
            //textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
            //textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Password not valid", Toast.LENGTH_SHORT).show();
            //textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;

                //textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                Toast.makeText(getApplicationContext(), "Password is to short", Toast.LENGTH_SHORT).show();
                //textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }





    public void openActivity(){

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}
