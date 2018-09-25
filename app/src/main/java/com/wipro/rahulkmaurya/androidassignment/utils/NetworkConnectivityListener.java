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
        int status = NetworkUtil.getConnectivityStatusString(context);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            Toast.makeText(context, status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED ? "Please check your internet connection." : "Bravo! You came online.", Toast.LENGTH_SHORT).show();
        }
    }
}
