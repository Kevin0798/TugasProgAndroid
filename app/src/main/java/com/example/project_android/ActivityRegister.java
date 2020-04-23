package com.example.project_android;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityRegister extends AppCompatActivity {
    EditText userName;
    EditText userEmail;
    EditText passUser;
    EditText usrName;
    EditText pssWord;
    EditText usrEmail;
    /*TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;*/

    Button btnRegister;
    private FirebaseFirestore firebaseFirestoreDb;


    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        sqliteHelper = new SqliteHelper(this);
        btnRegister = findViewById(R.id.buttonRegister);

        usrName = findViewById(R.id.userName);
        usrEmail = findViewById(R.id.userEmail);
        pssWord = findViewById(R.id.passUser);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usrName.getText().toString().isEmpty() && !usrEmail.getText().toString().isEmpty() && !pssWord.getText().toString().isEmpty()){
                    addRegisterData();
                }else{
                    Toast.makeText(ActivityRegister.this, "Semua kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String name = userName.getText().toString();
                    String email = userEmail.getText().toString();
                    String password = passUser.getText().toString();

                    if (!sqliteHelper.isEmailExists(email)) {
                        sqliteHelper.addUser(new User(null, name, email, password));
                        Toast.makeText(getApplicationContext(), "Berhasil daftar", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Email tidak Tersedia", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/

    }

    public void addRegisterData(){
        User user = new User(usrName.getText().toString(), usrEmail.getText().toString(), pssWord.getText().toString());
        firebaseFirestoreDb.collection("Register").document().set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ActivityRegister.this, "Berhasil Register", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActivityRegister.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());

                    }
                });
    }

    /*private void initTextViewLogin(){
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }*/

    public boolean validate() {
        boolean valid = false;

        String Username = userName.getText().toString();
        String email = userEmail.getText().toString();
        String Password = passUser.getText().toString();

        if (Username.isEmpty()) {
            valid = true;
            Toast.makeText(getApplicationContext(), "Username salah", Toast.LENGTH_SHORT).show();
        } else {
            if (Username.length() > 5) {
                valid = true;
                //textInputLayoutUserName.setError(null);
            } else {
                Toast.makeText(getApplicationContext(), "Username Terlalu pendek", Toast.LENGTH_SHORT).show();
            }
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid = false;
            Toast.makeText(getApplicationContext(), "Masukkan email yang benar", Toast.LENGTH_SHORT).show();
        }else{
            valid = true;
            //textInputLayoutEmail.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            Toast.makeText(getApplicationContext(), "Masukkan password yang benar", Toast.LENGTH_SHORT).show();
        } else {
            if (Password.length() > 5) {
                valid = true;
                //textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                Toast.makeText(getApplicationContext(), "Password terlalu pendek", Toast.LENGTH_SHORT).show();
            }
        }
        return valid;

    }
}