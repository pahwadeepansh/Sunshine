/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.preference.PreferenceManager;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ForecastFragment extends Fragment {

    private RecyclerView listView;
    private CustomRecyclerViewAdapter mForecastAdapter;

    public ForecastFragment() {
    }


    //    int numOfDays;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

        ;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);


        ;
    }

    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            int numOfDays = Integer.parseInt(getSharedPreferences_Days());
            updateWeather();
            return true;


        }
//        if (id==R.id.)

        return super.onOptionsItemSelected(item);
    }

    private String getSharedPreferences_Days() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return (sharedPrefs.getString(getActivity().getString(R.string.pref_days_key),
                getActivity().getString(R.string.pref_days_default)));
    }

    private String getSharedPreference_Zip() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return (sharedPrefs.getString(getActivity().getString(R.string.pref_location_key),
                getActivity().getString(R.string.pref_location_default)));

    }

    private String getSharedPreference_Units() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return (sharedPrefs.getString(getActivity().getString(R.string.pref_units_key),
                getActivity().getString(R.string.pref_units_metric)));


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The ArrayAdapter will take data from a source and
        // use it to populate the ListView it's attached to.


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (RecyclerView) rootView.findViewById(R.id.listview_forecast);
        listView.addItemDecoration(new DividerItemDecoration(getActivity()));
        listView.setHasFixedSize(true);


        // Get a reference to the ListView, and attach this adapter to it.
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);
        listView.setAdapter(mForecastAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                DayWeather forecast = mForecastAdapter.getItem(position);
//                Intent intent = new Intent(getActivity(), DetailActivity.class)
//                        .putExtra(Intent.EXTRA_TEXT, "DayWeather Forecast");
//                startActivity(intent);
//            }
//        });

        return rootView;
    }

    private ArrayList<DayWeather> getWeatherDataFromJson(Forecast forecast, int numDays) {

        // Thized UTC date for all of our weather.
        //numDays=Integer.parseInt(getSharedPreferences_Days());
        Time dayTime = new Time();
        dayTime.setToNow();

        // we start at the day returned by local time. Otherwise this is a mess.
        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

        // now we work exclusively in UTC
        dayTime = new Time();
        ArrayList<DayWeather> resultStrs = new ArrayList<DayWeather>();
        String unitType = getSharedPreference_Units();
        Log.e("ERROR numdays", String.valueOf(numDays));
        for (int i = 0; i < numDays; i++) {//hardcoded
            // For now, using the format "Day, description, hi/low"
            String day;
            String description;
            String[] highAndLow;
            String pressure;
            String humidity;
            String dayAverage;

            // Get the JSON object representing the day
            data dayForecast = forecast.forecastDays.get(i);

            // The date/time is returned as a long.  We need to convert that
            // into something human-readable, since most people won't read "1400356800" as
            // "this saturday".
            long dateTime;
            // Cheating to convert this to UTC time, which is what we want anyhow
            dateTime = dayTime.setJulianDay(julianStartDay + i);
            day = getReadableDateString(dateTime);

            // description is in a child array called "weather", which is 1 element long.
            Weather weatherObject = dayForecast.weather.get(0);
            description = weatherObject.description;

            pressure = String.valueOf(dayForecast.pressure);
            humidity = String.valueOf(dayForecast.humidity);
            dayAverage = String.valueOf(dayForecast.temp.day);

            // Temperatures are in a child object called "temp".  Try not to name variables
            // "temp" when working with temperature.  It confuses everybody.
            temp temperatureObject = dayForecast.temp;
            double high = temperatureObject.max;
            double low = temperatureObject.min;

            highAndLow = formatHighLows(high, low, unitType);
            resultStrs.add(i, new DayWeather(Parcel.obtain()));
            resultStrs.get(i).day =day;
            resultStrs.get(i).description = description;
            resultStrs.get(i).high = highAndLow[0];
            resultStrs.get(i).low = highAndLow[1];
            resultStrs.get(i).humidity = humidity;
            resultStrs.get(i).pressure = pressure;
            resultStrs.get(i).dayAverage =  Integer.toString(((int)Double.parseDouble(dayAverage)));
        }
        return resultStrs;

    }

    private CurrentWeather getCurrentWeatherDataFromJSON(CurrentForecast forecast) {
        String unitType = getSharedPreference_Units();
        CurrentWeather result = new CurrentWeather();// delete
        result.current_temp = roundOff(forecast.main.temp, unitType);
        result.city = forecast.city;
        return result; //delete

    }

    private String getReadableDateString(long time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }

    private String[] formatHighLows(double celsiusHigh, double celsiusLow, String desiredUnitType) {
        return formatHighLows(
                celsiusHigh,
                celsiusLow,
                desiredUnitType,
                getActivity().getString(R.string.pref_units_imperial));
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    @VisibleForTesting
    String[] formatHighLows(double celsiusHigh, double celsiusLow, String desiredUnitType, String imperialUnitTypeName) {

        if (desiredUnitType.equals(imperialUnitTypeName)) {
            celsiusHigh = (celsiusHigh * 1.8) + 32;
            celsiusLow = (celsiusLow * 1.8) + 32;
        } else if (!desiredUnitType.equals("metric")) {
            Log.d("LOGGGGG", "Unit type not found: " + desiredUnitType);
        }

        // For presentation, assume the user doesn't care about tenths of a degree.
        long roundedHigh = Math.round(celsiusHigh);
        long roundedLow = Math.round(celsiusLow);

        String[] highLowStr = new String[2];
        highLowStr[0] = String.valueOf(roundedHigh);
        highLowStr[1] = String.valueOf(roundedLow);

        return highLowStr;
    }


    private String roundOff(BigDecimal temp, String requiredUnitType) {
        double calc = temp.doubleValue();
        if (requiredUnitType.equals(getActivity().getString(R.string.pref_units_imperial))) {
            calc = (temp.doubleValue() * 1.8) + 32;

        } else if (!requiredUnitType.equals(getActivity().getString(R.string.pref_units_metric))) {
            Log.d("LOGGGGG", "Unit type not found: " + requiredUnitType);
        }

        // For presentation, assume the user doesn't care about tenths of a degree.
        long roundedHigh = Math.round(calc);

        String highLowStr = String.valueOf(roundedHigh);

        return highLowStr;
    }

    ArrayList<DayWeather> result = new ArrayList<DayWeather>();
    CurrentWeather CurrentTemp = new CurrentWeather();

    private void updateWeather() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        String zip = getSharedPreference_Zip().trim();
        String units = getSharedPreference_Units().trim();

        Log.e("ERROR of days", String.valueOf(Integer.parseInt(getSharedPreferences_Days().trim())));
        Call<Forecast> call = weatherAPI.loadForecast(zip, units, Integer.parseInt(getSharedPreferences_Days().trim()));
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Forecast forecast = response.body();
                    Log.e("ERROR of days", String.valueOf(Integer.parseInt(getSharedPreferences_Days().trim())));
                    result = getWeatherDataFromJson(forecast, (Integer.parseInt(getSharedPreferences_Days().trim())));
                    Log.e("ERROR Forecast", result.get(1).high);
                    Log.e("ERROR Forecast", result.get(1).description);
                    updateAdapter();
                }
                if (!response.isSuccess()) {
                    Log.e("ERROR", String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("ERROR", "WTH? in first retrofit connection", t);
            }
        });

//second retrofit network starts
        Retrofit retrofitNow = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();
        CurrentWeatherAPI weatherNow = retrofitNow.create(CurrentWeatherAPI.class);
        String zip_now = getSharedPreference_Zip();
        String units_now = getSharedPreference_Units();
        Call<CurrentForecast> callNow = weatherNow.loadForecast(zip_now, units_now);
        callNow.enqueue(new Callback<CurrentForecast>() {
            @Override
            public void onResponse(Response<CurrentForecast> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    CurrentForecast forecast = response.body();
                    CurrentTemp = getCurrentWeatherDataFromJSON(forecast);
                    Log.e("ERROR CurrentForecast", CurrentTemp.current_temp);
                    Log.e("ERROR CurrentForecast", CurrentTemp.city);

                    updateAdapter();

                }
                if (!response.isSuccess()) {
                    Log.e("ERROR", String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("ERROR", "WTH? in second retrofit connection", t);
            }


        });
        //second retrofit network ends

        updateAdapter();
        //  Log.e("ERROR", String.valueOf(mForecastAdapter.getCount()));
    }

    private void updateAdapter() {
        mForecastAdapter =
                new CustomRecyclerViewAdapter(
                        getActivity(), // The current activity (this activity)
                        result, CurrentTemp);// Add a shared preference
        listView.setAdapter(mForecastAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWeather();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

}
