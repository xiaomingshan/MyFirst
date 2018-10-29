package com.bm.mytestdemo.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.util.Log;

/**
 * Created by xiao on 2018/9/20.
 */

public class MyApplication extends Application {
    ConnectivityManager cm;
    @Override
    public void onCreate() {
        super.onCreate();
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //intent.setAction("android.mytestdemo.netsend");
        /*cm.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager
                .NetworkCallback() {

            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                switch (cm.getNetworkInfo(network).getType()){
                    case ConnectivityManager.TYPE_WIFI:
                        Log.e("@@@","wifi网络"+cm.getNetworkInfo(network).getType());
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        Log.e("@@@","手机网络"+cm.getNetworkInfo(network).getType());
                        break;
                }
            }
        });*/

        cm.registerNetworkCallback(new NetworkRequest.Builder().build(), new ConnectivityManager
                .NetworkCallback() {
            @Override
            public void onLost(Network network) {
                super.onLost(network);
                if (getConnectedType() == -1){
                    Log.e("@@@", "aaaaaaa"+getConnectedType());
                }
            }

            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                switch (cm.getNetworkInfo(network).getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        Log.e("@@@", "wifi网络" + cm.getNetworkInfo(network).getType());
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        Log.e("@@@", "手机网络" + cm.getNetworkInfo(network).getType());
                        break;
                }
            }
        });

    }

    public int getConnectedType() {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            return mNetworkInfo.getType();
        }

        return -1;
    }
}
