package com.example.android.sunshine.app.Model.ForecastModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deepanshpahwa on 2/19/16.
 */

public class Forecast {
    @SerializedName("list")
    public
    List<data> forecastDays;
}


