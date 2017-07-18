package com.example.android.quakereport.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import com.example.android.quakereport.Adapter.MainQuakeListAdapter;
import com.example.android.quakereport.Quake.GEOJSONURLSetter;
import com.example.android.quakereport.Quake.QuakeData;
import com.example.android.quakereport.JSONNetworkConnection.JSONHTTPReceiver;
import com.example.android.quakereport.JSONParsing.QuakeDataJSONParser;
import com.example.android.quakereport.R;
import com.example.android.quakereport.ReceyclerViewDecoration.SimpleDividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnSharedPreferenceChangeListener {

    private RecyclerView recyclerView;
    private ProgressBar loadingDataProgressBar;
    private TextView loadingDataTextView;
    private TextView noInternetTextView;
    private Button tryAgainButton;
    private boolean menuItemVisibleState;

    private ArrayList<QuakeData> quakeDataArrayList;
    private int maxMagnitude;
    private int minMagnitude;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        loadingDataProgressBar = (ProgressBar) findViewById(R.id.pb_still_loading);
        loadingDataTextView = (TextView) findViewById(R.id.txt_still_loading);
        noInternetTextView = (TextView) findViewById(R.id.txt_no_internet);
        tryAgainButton = (Button) findViewById(R.id.btn_try_again);

        TryAgainOnClickListener tryAgainOnClickListener = new TryAgainOnClickListener();
        tryAgainButton.setOnClickListener(tryAgainOnClickListener);

        recyclerView.setVisibility(View.INVISIBLE);

        SetInitialPreferences();
        GetQuakeData(minMagnitude, maxMagnitude);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem refreshMenuItem = menu.findItem(R.id.item_menu_refresh);

        if(menuItemVisibleState)
        {
            refreshMenuItem.setVisible(true);
        }

        else
        {
            refreshMenuItem.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item_menu_refresh : GetQuakeData(minMagnitude, maxMagnitude); return true;
            case R.id.item_menu_setting : GoToSetting(); return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key.contains(getString(R.string.max_magnitude_key)) || key.contains(getString(R.string.min_magnitude_key)))
        {
            if(key.contains(getString(R.string.max_magnitude_key)))
            {
                maxMagnitude = Integer.parseInt(sharedPreferences.getString(key, "10"));
            }

            else
            {
                minMagnitude = Integer.parseInt(sharedPreferences.getString(key, "0"));
            }

            GetQuakeData(minMagnitude, maxMagnitude);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void SetInitialPreferences()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        maxMagnitude = Integer.parseInt(sharedPreferences.getString(getString(R.string.max_magnitude_key), getString(R.string.max_magnitude_defaultkey)));
        minMagnitude = Integer.parseInt(sharedPreferences.getString(getString(R.string.min_magnitude_key), getString(R.string.min_magnitude_defaultkey)));

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void GetQuakeData(int minMagnitude, int maxMagnitude)
    {
        QuakeDataGetterAsyncTask quakeDataGetterAsyncTask = new QuakeDataGetterAsyncTask(getApplicationContext());

        String mainQuakeURL = GEOJSONURLSetter.GetURL(minMagnitude, maxMagnitude);
        quakeDataGetterAsyncTask.execute(mainQuakeURL);
    }

    private void SetRecyclerView()
    {
        MainQuakeListAdapter mainQuakeListAdapter = new MainQuakeListAdapter(quakeDataArrayList, this);
        recyclerView.setAdapter(mainQuakeListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(this);
        recyclerView.addItemDecoration(simpleDividerItemDecoration);

        recyclerView.setHasFixedSize(true);
    }

    private void GoToSetting()
    {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    private class QuakeDataGetterAsyncTask extends AsyncTask<String, Void, ArrayList<QuakeData>>
    {
        final JSONHTTPReceiver jsonhttpReceiver;
        final QuakeDataJSONParser quakeDataJSONParser;
        final Context context;

        private QuakeDataGetterAsyncTask(Context context)
        {
            this.context = context;

            jsonhttpReceiver = new JSONHTTPReceiver();
            quakeDataJSONParser = new QuakeDataJSONParser();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            if(recyclerView.getVisibility() == View.VISIBLE)
            {
                recyclerView.setVisibility(View.INVISIBLE);
            }

            if(noInternetTextView.getVisibility() == View.VISIBLE || tryAgainButton.getVisibility() == View.VISIBLE)
            {
                noInternetTextView.setVisibility(View.INVISIBLE);
                tryAgainButton.setVisibility(View.INVISIBLE);
            }

            loadingDataProgressBar.setVisibility(View.VISIBLE);
            loadingDataTextView.setVisibility(View.VISIBLE);

            menuItemVisibleState = false;
            invalidateOptionsMenu();

            if(!jsonhttpReceiver.CheckConnection(context))
            {
                cancel(true);

                loadingDataProgressBar.setVisibility(View.INVISIBLE);
                loadingDataTextView.setVisibility(View.INVISIBLE);

                noInternetTextView.setVisibility(View.VISIBLE);
                tryAgainButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onPostExecute(ArrayList<QuakeData> quakeDatas)
        {
            super.onPostExecute(quakeDatas);

            quakeDataArrayList = quakeDatas;

            SetRecyclerView();

            recyclerView.setVisibility(View.VISIBLE);
            loadingDataProgressBar.setVisibility(View.INVISIBLE);
            loadingDataTextView.setVisibility(View.INVISIBLE);

            menuItemVisibleState = true;
            invalidateOptionsMenu();
        }

        @Override
        protected ArrayList<QuakeData> doInBackground(String... params)
        {
            String URL = params[0];

            if(isCancelled())
            {
                return null;
            }

            JSONObject jsonObject = jsonhttpReceiver.ReceiveData(URL);
            return quakeDataJSONParser.Parse(jsonObject);
        }
    }

    private class TryAgainOnClickListener implements Button.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            noInternetTextView.setVisibility(View.INVISIBLE);
            tryAgainButton.setVisibility(View.INVISIBLE);

            GetQuakeData(minMagnitude, maxMagnitude);
        }
    }




}


