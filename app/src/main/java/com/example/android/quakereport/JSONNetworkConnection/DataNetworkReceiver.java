package com.example.android.quakereport.JSONNetworkConnection;

import android.content.Context;

/**
 * Created by nugroho on 03/07/17.
 */

interface DataNetworkReceiver<DataType>
{
    DataType ReceiveData(String URL);
    boolean CheckConnection(Context context);
}
