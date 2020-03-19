package com.example.project_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class WifiStateChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent){

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity){
                Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
            }
        }

    }

}
