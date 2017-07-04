package com.example.android.quakereport.Quake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.example.android.quakereport.R;

/**
 * Created by nugroho on 03/07/17.
 */

public class QuakeCircleColor
{
    public static void GetColor(double magnitude, TextView textView, Context context)
    {
        int colorResourceForMagnitudeImage = GetColorResourceForMagnitudeImage(magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) textView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = colorResourceForMagnitudeImage;

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(ContextCompat.getColor(context, magnitudeColor));
    }


    static int GetColorResourceForMagnitudeImage(double magnitude)
    {
        if(magnitude >= 0 && magnitude < 2)
        {
            return  R.color.magnitude1;
        }

        else if(magnitude >= 2 && magnitude < 3)
        {
            return R.color.magnitude2;
        }

        else if(magnitude >= 3 && magnitude < 4)
        {
            return R.color.magnitude3;
        }

        else if(magnitude >= 4 && magnitude < 5)
        {
            return R.color.magnitude4;
        }

        else if(magnitude >= 5 && magnitude < 6)
        {
            return R.color.magnitude5;
        }

        else if(magnitude >= 6 && magnitude < 7)
        {
            return R.color.magnitude6;
        }

        else if(magnitude >= 7 && magnitude < 8)
        {
            return R.color.magnitude7;
        }

        else if(magnitude >= 8 && magnitude < 9)
        {
            return R.color.magnitude8;
        }

        else if(magnitude >= 9 && magnitude < 10)
        {
            return R.color.magnitude9;
        }

        else if(magnitude >= 10)
        {
            return R.color.magnitude10plus;
        }

        else
        {
            return R.color.colorPrimaryDark;
        }
    }
}
