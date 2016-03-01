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


    public void DayWeather(String day,String high, String low, String description){
        this.day=day;
        this.high=high;
        this.low=low;
        this.description=description;
    }

}
