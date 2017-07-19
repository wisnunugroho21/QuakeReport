package com.example.android.quakereport.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import com.example.android.quakereport.R;

/**
 * Created by nugroho on 18/07/17.
 */

public class SettingFragment extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener
{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        addPreferencesFromResource(R.xml.quake_preferences);

        SetInitialPreferencesSummary();
    }

    private void SetInitialPreferencesSummary()
    {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        for(int a = 0; a < preferenceScreen.getPreferenceCount(); a++)
        {
            Preference preference = preferenceScreen.getPreference(a);

            String value = sharedPreferences.getString(preference.getKey(), "");
            SetPreferencesSummary(preference, value);
        }
    }

    private void SetPreferencesSummary(Preference preference, String value)
    {
        if(preference instanceof ListPreference)
        {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(value);

            if(index >= 0)
            {
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        Preference preference = findPreference(key);

        String value = sharedPreferences.getString(key, "");
        SetPreferencesSummary(preference, value);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
