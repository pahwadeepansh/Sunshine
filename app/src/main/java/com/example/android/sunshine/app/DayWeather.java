package com.example.android.sunshine.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public class DayWeather {
    String day;
    String high;
    String low;
    String description;
    String pressure;
    String humidity;
    String dayAverage;


    public void DayWeather(String day,String high, String low, String description,String pressure, String humidity){
        this.day=day;
        this.high=high;
        this.low=low;
        this.description=description;
        this.pressure=pressure;
        this.humidity=humidity;
        this.dayAverage=dayAverage;
    }

}
