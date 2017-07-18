package com.example.android.quakereport.Quake;

import java.util.GregorianCalendar;

/**
 * Created by nugroho on 02/07/17.
 */

public class QuakeData
{
    private final String urlQuake;
    private final double quakeMagnitude;
    private final String quakeLocation;
    private final GregorianCalendar quakeDateTime;

    public QuakeData(String urlQuake, double quakeMagnitude, String quakeLocation, GregorianCalendar quakeDateTime)
    {
        this.urlQuake = urlQuake;
        this.quakeMagnitude = quakeMagnitude;
        this.quakeLocation = quakeLocation;
        this.quakeDateTime = quakeDateTime;
    }

    public double getQuakeMagnitude()
    {
        return quakeMagnitude;
    }

    public String getQuakeLocation()
    {
        return quakeLocation;
    }

    public GregorianCalendar getQuakeDateTime()
    {
        return quakeDateTime;
    }

    public String getUrlQuake()
    {
        return urlQuake;
    }
}
