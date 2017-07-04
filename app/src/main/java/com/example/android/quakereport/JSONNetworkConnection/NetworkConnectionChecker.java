package com.example.android.quakereport.JSONNetworkConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by nugroho on 03/07/17.
 */

public class NetworkConnectionChecker
{
    Context context;

    public NetworkConnectionChecker(Context context)
    {
        this.context = context;
    }

    public boolean CheckConnection()
    {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isConnected())
        {
            return true;
        }

        else
        {
            return false;
        }
    }
}
