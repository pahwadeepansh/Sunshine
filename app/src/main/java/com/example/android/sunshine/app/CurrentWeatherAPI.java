package com.example.android.sunshine.app;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by deepansh on 2/23/16.
 */
public interface CurrentWeatherAPI {
//    @GET("/data/2.5/weather?zip={zip},us&units={units}&appid=44db6a862fba0b067b1930da0d769e98")
//    Call<CurrentForecast> loadForecast(@Path("zip")String zip,@Path("units") String units);

    @GET("/data/2.5/weather?appid=44db6a862fba0b067b1930da0d769e98")
    Call<CurrentForecast> loadForecast(@Query("zip") String zip, @Query("units") String units);
}
