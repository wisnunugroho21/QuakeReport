package com.example.android.quakereport.JSONNetworkConnection;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by nugroho on 03/07/17.
 */

public interface JSONReceiver extends DataNetworkReceiver<JSONObject>
{
    @Override
    JSONObject ReceiveData(String URL);

    @Override
    boolean CheckConnection(Context context);
}
