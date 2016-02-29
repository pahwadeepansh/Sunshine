package com.example.android.sunshine.app;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by deepansh on 2/23/16.
 */
public class CurrentForecast {
    Main main;
    @SerializedName("name")
    String city;

}

class Main{
    BigDecimal temp;
}
