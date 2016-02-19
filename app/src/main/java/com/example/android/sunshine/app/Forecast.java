package com.example.android.sunshine.app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deepanshpahwa on 2/19/16.
 */


class Weather {
    int id;
    String main;
    String description;

}
  class temp{
    float day;
    float min;
    float max;
    float night;
    float eve;
    float morn;
}

 class data{
    double dt;
    temp temp;
    float pressure;
    float humidity;
    List<Weather> weather;
    float speed;
    float deg;
    float clouds;
    float snow;

}

 class Forecast {
    @SerializedName("list")
    List<data> forecastDays;
}
