package com.wipro.rahulkmaurya.androidassignment.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Class used to listen network connectivity if Internet is connected/disconnected
 * Created by rahul.k.maurya on 2018-09-25.
 *
 */

public class NetworkConnectivityListener extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.getAction()
                && NetworkUtil.getConnectivityStatusString(context)  == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
            Toast.makeText(context, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
        }
    }
}
