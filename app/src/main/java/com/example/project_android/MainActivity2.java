package com.example.project_android;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}
