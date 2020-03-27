package com.example.project_android;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SynopsisActivity extends AppCompatActivity {
    ImageView mainImageView;
    TextView title, description, synopsis;
    String title2, synopsis2;
    String sinopsis3 = "Sinopsis";
    int photos2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_movies);

        mainImageView= findViewById(R.id.myImage);
        title= findViewById(R.id.judul);
        description= findViewById(R.id.dtlDeskripsi);
        synopsis = findViewById(R.id.detailDeskripsi);
        getData();
        setData();
    }

    private void setData(){
        title.setText(title2);
        description.setText(synopsis2);
        synopsis.setText(sinopsis3);
        mainImageView.setImageResource(photos2);
    }

    private void getData(){
        if(getIntent().hasExtra("Photos") && getIntent().hasExtra("Title") && getIntent().hasExtra("Synopsis")){
            title2 = getIntent().getStringExtra("title");
            sinopsis3 = getIntent().getStringExtra("Synopsis");
            photos2 = getIntent().getIntExtra("Photos", 1);
        }else{
            Toast.makeText(this,"no Data", Toast.LENGTH_SHORT).show();
        }

    }



}
