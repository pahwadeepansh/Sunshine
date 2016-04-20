package com.example.android.sunshine.app.Model;

import java.io.Serializable;

/**
 * Created by deepansh on 2/23/16.
 */
public class CurrentWeather {

    public String current_temp;
    public String city;

    public void DayWeather(String temp, String city){
        this.current_temp=temp;
        this.city=city;
    }
}
