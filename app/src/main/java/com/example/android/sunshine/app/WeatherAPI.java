package com.example.android.sunshine.app;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public interface WeatherAPI {
    @GET("/data/2.5/forecast/daily?q=cincinnati,us&units=metric&cnt=7&APPID=079d2efd21e734f3d4384504fbf7792c")
     Call<Forecast> loadForecast(@Query("Tagged") String tags);

}
