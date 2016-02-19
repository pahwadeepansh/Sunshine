package com.example.android.sunshine.app;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public class DayWeather {
    String day;
    double high;
    double low;
    String description;

    public void DayWeather(String day,int high, int low, String description){
        this.day=day;
        this.high=high;
        this.low=low;
        this.description=description;
    }


}
