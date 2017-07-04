package com.example.android.quakereport.Quake;

import android.widget.TextView;

/**
 * Created by nugroho on 03/07/17.
 */

public class QuakeLocationTextViewSetter
{

    public static void SetQuakeLocationTextView(TextView distanceLocationTextView, TextView locationPreferenceTextView, QuakeData quakeData)
    {
        if(quakeData.getQuakeLocation().contains(" of "))
        {
            String location[] = quakeData.getQuakeLocation().split(" of ");
            location[0] += " OF ";

            distanceLocationTextView.setText(location[0]);
            locationPreferenceTextView.setText(location[1]);
        }

        else
        {
            distanceLocationTextView.setText("");
            locationPreferenceTextView.setText(quakeData.getQuakeLocation());
        }
    }
}
