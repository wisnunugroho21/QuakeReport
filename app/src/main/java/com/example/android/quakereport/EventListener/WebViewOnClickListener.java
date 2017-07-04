package com.example.android.quakereport.EventListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nugroho on 02/07/17.
 */

public class WebViewOnClickListener implements RecyclerView.OnClickListener
{
    String URL;
    Context context;

    public WebViewOnClickListener(String URL, Context context)
    {
        this.URL = URL;
        this.context = context;
    }

    @Override
    public void onClick(View v)
    {
        Uri uri = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
