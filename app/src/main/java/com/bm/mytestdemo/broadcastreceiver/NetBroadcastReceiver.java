package com.bm.mytestdemo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by xiao on 2018/9/20.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    ConnectivityManager cm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("@@@","111111");
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info.getType() == ConnectivityManager.TYPE_WIFI){

        }

    }
}
