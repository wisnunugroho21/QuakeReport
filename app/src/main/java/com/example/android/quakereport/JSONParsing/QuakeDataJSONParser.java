package com.example.android.quakereport.JSONParsing;

import com.example.android.quakereport.Quake.QuakeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by nugroho on 03/07/17.
 */

public class QuakeDataJSONParser implements JSONParser<ArrayList<QuakeData>>
{
    @Override
    public ArrayList<QuakeData> Parse(JSONObject jsonObject)
    {
        ArrayList<QuakeData> quakeDataList = new ArrayList<>();

        try
        {
            JSONArray features = jsonObject.getJSONArray("features");

            for (int a = 0; a < features.length(); a++)
            {
                JSONObject feature = features.getJSONObject(a);
                JSONObject properties = feature.getJSONObject("properties");
                Long milisTimeQuake = properties.getLong("time");

                GregorianCalendar dateTimeQuake = new GregorianCalendar();
                dateTimeQuake.setTimeInMillis(milisTimeQuake);

                double magQuake = properties.getDouble("mag");
                String placeQuake = properties.getString("place");
                String URL = properties.getString("url");

                QuakeData quakeData = new QuakeData(URL, magQuake, placeQuake, dateTimeQuake);
                quakeDataList.add(quakeData);
            }
            
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return quakeDataList;
    }
}
