package com.example.android.quakereport.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.quakereport.Quake.AM_PMIndexer;
import com.example.android.quakereport.Quake.MonthNameIndexer;
import com.example.android.quakereport.Quake.QuakeCircleColor;
import com.example.android.quakereport.Quake.QuakeData;
import com.example.android.quakereport.EventListener.WebViewOnClickListener;
import com.example.android.quakereport.Quake.QuakeDateTimeTextViewSetter;
import com.example.android.quakereport.Quake.QuakeLocationTextViewSetter;
import com.example.android.quakereport.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by nugroho on 02/07/17.
 */

public class MainQuakeListAdapter extends RecyclerView.Adapter<MainQuakeListViewHolder>
{
    ArrayList<QuakeData> quakeDataArrayList;
    Context context;

    public MainQuakeListAdapter(ArrayList<QuakeData> quakeDataArrayList, Context context)
    {
        this.quakeDataArrayList = quakeDataArrayList;
        this.context = context;
    }

    @Override
    public MainQuakeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_item_list, parent, false);
        return new MainQuakeListViewHolder(itemView, quakeDataArrayList, context);
    }

    @Override
    public void onBindViewHolder(MainQuakeListViewHolder holder, int position)
    {
        holder.Bind(position);
    }

    @Override
    public int getItemCount()
    {
        return quakeDataArrayList.size();
    }
}

class MainQuakeListViewHolder extends RecyclerView.ViewHolder
{
    ArrayList<QuakeData> quakeDataArrayList;
    Context context;
    View itemView;

    TextView magnitudeTextView;
    TextView distanceLocationTextView;
    TextView locationPreferenceTextView;
    TextView dateTextView;
    TextView timeTextView;


    public MainQuakeListViewHolder(View itemView, ArrayList<QuakeData> quakeDataArrayList, Context context)
    {
        super(itemView);

        this.itemView = itemView;
        this.quakeDataArrayList = quakeDataArrayList;
        this.context = context;

        magnitudeTextView = (TextView) this.itemView.findViewById(R.id.magnitude);
        distanceLocationTextView = (TextView) this.itemView.findViewById(R.id.location_offset);
        locationPreferenceTextView = (TextView) this.itemView.findViewById(R.id.primary_location);
        dateTextView = (TextView) this.itemView.findViewById(R.id.date);
        timeTextView = (TextView) this.itemView.findViewById(R.id.time);
    }

    public void Bind(int position)
    {
        QuakeData quakeData = quakeDataArrayList.get(position);

        QuakeCircleColor.GetColor(quakeData.getQuakeMagnitude(), magnitudeTextView, context);
        magnitudeTextView.setText(String.valueOf(quakeData.getQuakeMagnitude()));

        QuakeLocationTextViewSetter.SetQuakeLocationTextView(distanceLocationTextView, locationPreferenceTextView, quakeData);
        QuakeDateTimeTextViewSetter.SetQuakeDateTimeTextView(dateTextView, timeTextView, quakeData);

        WebViewOnClickListener webViewOnClickListener = new WebViewOnClickListener(quakeData.getUrlQuake(), context);
        itemView.setOnClickListener(webViewOnClickListener);
    }

}
