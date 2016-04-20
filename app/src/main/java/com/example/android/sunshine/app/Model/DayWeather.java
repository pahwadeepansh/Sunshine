package com.example.android.sunshine.app.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public class DayWeather implements Parcelable{
    public String day;
    public String high;
    public String low;
    public String description;
    public String pressure;
    public String humidity;
    public String dayAverage;


    public void DayWeather(String day,String high, String low, String description,String pressure, String humidity, String dayAverage){
        this.day=day;
        this.high=high;
        this.low=low;
        this.description=description;
        this.pressure=pressure;
        this.humidity=humidity;
        this.dayAverage=dayAverage;
    }

    public DayWeather(Parcel in) {
        day = in.readString();
        high = in.readString();
        low = in.readString();
        description = in.readString();
        pressure = in.readString();
        humidity = in.readString();
        dayAverage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
        dest.writeString(high);
        dest.writeString(low);
        dest.writeString(description);
        dest.writeString(pressure);
        dest.writeString(humidity);
        dest.writeString(dayAverage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DayWeather> CREATOR = new Parcelable.Creator<DayWeather>() {
        @Override
        public DayWeather createFromParcel(Parcel in) {
            return new DayWeather(in);
        }

        @Override
        public DayWeather[] newArray(int size) {
            return new DayWeather[size];
        }
    };

}
