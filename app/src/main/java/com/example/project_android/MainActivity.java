package com.example.project_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView EmailText;
    private  TextView PasswordText;
    private  Button Loginbtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailText = findViewById(R.id.emailText);
        PasswordText = findViewById(R.id.passwordText);
        Loginbtn = findViewById(R.id.loginButton);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EmailText.getText().toString().matches(emailPattern)){
                    if (!EmailText.getText().toString().isEmpty() && !PasswordText.getText().toString().isEmpty() ){
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        openActivity();
                    }

                }
                if (EmailText.getText().toString().isEmpty()&& !PasswordText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email is empty !", Toast.LENGTH_SHORT).show();
                }
                if (!EmailText.getText().toString().isEmpty() && PasswordText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password is empty !", Toast.LENGTH_SHORT).show();
                }
                if (EmailText.getText().toString().isEmpty() && PasswordText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email dan Password is Empty", Toast.LENGTH_SHORT).show();
                }
                if (!EmailText.getText().toString().matches(emailPattern)){
                    Toast.makeText(getApplicationContext(), "Email harus menggunakan @gmail.com atau @yahoo.com", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
    public void openActivity(){

        Intent intent = new Intent(this, MainActivity2.class);

        startActivity(intent);
    }

}
