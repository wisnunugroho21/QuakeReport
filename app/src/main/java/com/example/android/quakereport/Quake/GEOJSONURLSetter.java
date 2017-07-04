package com.example.android.quakereport.Quake;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by nugroho on 03/07/17.
 */

public class GEOJSONURLSetter
{
    public static String GetURL()
    {
        return "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + GetStartCurrentDateString() + "&endtime=" + GetEndCurrentDateString();
    }

    static String GetStartCurrentDateString()
    {
        Calendar calendar = GregorianCalendar.getInstance();
        String dateString = String.valueOf(calendar.get(Calendar.YEAR)) + "-";

        int monthIndex = calendar.get(Calendar.MONTH) + 1;

        if(monthIndex < 10)
        {
            dateString += "0" + String.valueOf(monthIndex);
        }

        else
        {
            dateString += String.valueOf(monthIndex);
        }

        dateString += "-";

        int dayIndex =  calendar.get(Calendar.DAY_OF_MONTH) - 2;

        if(dayIndex < 10)
        {
            dateString += "0" + String.valueOf(dayIndex);
        }

        else
        {
            dateString += String.valueOf(dayIndex);
        }

        return  dateString;
    }

    static String GetEndCurrentDateString()
    {
        Calendar calendar = GregorianCalendar.getInstance();
        String dateString = String.valueOf(calendar.get(Calendar.YEAR)) + "-";

        int monthIndex = calendar.get(Calendar.MONTH) + 1;

        if(monthIndex < 10)
        {
            dateString += "0" + String.valueOf(monthIndex);
        }

        else
        {
            dateString += String.valueOf(monthIndex);
        }

        dateString += "-";

        int dayIndex =  calendar.get(Calendar.DAY_OF_MONTH) - 1;

        if(dayIndex < 10)
        {
            dateString += "0" + String.valueOf(dayIndex);
        }

        else
        {
            dateString += String.valueOf(dayIndex);
        }

        return  dateString;
    }



}
