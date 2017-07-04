package com.example.android.quakereport.JSONParsing;

import org.json.JSONObject;

/**
 * Created by nugroho on 03/07/17.
 */

public interface JSONParser<DataType>
{
    DataType Parse(JSONObject jsonObject);
}
