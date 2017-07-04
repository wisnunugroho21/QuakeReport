package com.example.android.quakereport.Quake;

import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by nugroho on 03/07/17.
 */

public class QuakeDateTimeTextViewSetter
{
    public QuakeDateTimeTextViewSetter(TextView dateTextView, TextView timeTextView, QuakeData quakeData) {
        this.dateTextView = dateTextView;
        this.timeTextView = timeTextView;
        this.quakeData = quakeData;
    }

    TextView dateTextView;
    TextView timeTextView;
    QuakeData quakeData;

    public static void SetQuakeDateTimeTextView(TextView dateTextView, TextView timeTextView, QuakeData quakeData)
    {
        String date = quakeData.getQuakeDateTime().get(Calendar.DAY_OF_MONTH) + " " +
                MonthNameIndexer.GetMonthName(quakeData.getQuakeDateTime().get(Calendar.MONTH)) + " " +
                quakeData.getQuakeDateTime().get(Calendar.YEAR);

        String time = "";
        int hour = quakeData.getQuakeDateTime().get(Calendar.HOUR);

        if(hour < 10)
        {
            time += "0" + hour;
        }

        else
        {
            time += hour;
        }

        time += ":";
        int minute = quakeData.getQuakeDateTime().get(Calendar.MINUTE);

        if(minute < 10)
        {
            time += "0" + minute;
        }

        else
        {
            time += minute;
        }

        time += " " + AM_PMIndexer.GetAM_PM(quakeData.getQuakeDateTime().get(Calendar.AM_PM));

        dateTextView.setText(date);
        timeTextView.setText(time);
    }
}
