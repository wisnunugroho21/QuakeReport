package com.example.android.quakereport.JSONNetworkConnection;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by nugroho on 03/07/17.
 */

public class JSONHTTPReceiver implements JSONReceiver
{
    @Override
    public JSONObject ReceiveData(String stringURL)
    {
        try
        {
            URL url = new URL(stringURL);
            HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            urlConnection.disconnect();

            if (hasInput)
            {
                return new JSONObject(scanner.next());
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean CheckConnection(Context context)
    {
        NetworkConnectionChecker networkConnectionChecker = new NetworkConnectionChecker(context);
        return networkConnectionChecker.CheckConnection();
    }
}
