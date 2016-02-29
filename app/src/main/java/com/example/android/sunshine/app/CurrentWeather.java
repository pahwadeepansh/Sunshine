package com.example.android.sunshine.app;

/**
 * Created by deepansh on 2/23/16.
 */
public class CurrentWeather {

    String current_temp;
    String city;

    public void DayWeather(String temp, String city){
        this.current_temp=temp;
        this.city=city;
    }
}
