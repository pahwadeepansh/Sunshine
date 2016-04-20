package com.example.android.sunshine.app.Retrofit;

import com.example.android.sunshine.app.Model.ForecastModels.Forecast;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public interface WeatherAPI {
//    @GET("/data/2.5/forecast/daily?zip={zip},us&units={units}&cnt={days}&APPID=079d2efd21e734f3d4384504fbf7792c")
//    Call<Forecast> loadForecast ( @Path("zip")String zip,@Path("units")String units,@Path("days")String days);

    @GET("/data/2.5/forecast/daily?&APPID=079d2efd21e734f3d4384504fbf7792c")
    Call<Forecast> loadForecast(@Query("zip") String zip, @Query("units") String units, @Query("days") int days);

}
