package com.example.android.sunshine.app.Model.CurrentForecastModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deepansh on 2/23/16.
 */
public class CurrentForecast {
    public Main main;
    @SerializedName("name")
    public String city;

}
