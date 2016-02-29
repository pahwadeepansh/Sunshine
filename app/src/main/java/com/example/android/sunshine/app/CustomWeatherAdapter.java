package com.example.android.sunshine.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public class CustomWeatherAdapter extends ArrayAdapter<DayWeather> {
    Context context;
    DayWeather[] data = null;
    CurrentWeather currentTemp;
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public CustomWeatherAdapter(Context context, DayWeather[] data, CurrentWeather currentTemp) {
        super(context,0,data);
        this.context=context;
        //this.data= data;
        this.currentTemp= currentTemp;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        DayWeather data = getItem(position);

        int viewType = getItemViewType(position);
        if(viewType==1){
            if(convertView==null){
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView= inflater.inflate(R.layout.listview_item_row,parent,false);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.imgIcon);
                TextView dayDate = (TextView) convertView.findViewById(R.id.dayDate);
                TextView dayDesc = (TextView) convertView.findViewById(R.id.dayDesc);
                TextView dayTempHigh = (TextView) convertView.findViewById(R.id.dayTempHigh);
                TextView dayTempLow = (TextView) convertView.findViewById(R.id.dayTempLow);


                dayDate.setText(data.day );
                dayDesc.setText(data.description );
                dayTempHigh.setText((data.high+"°"));
                dayTempLow.setText((data.low+"°"));

                String description = data.description;

                if (descriptionContains("cloud",description)){
                    imageView.setImageResource(R.drawable.cloud);
                }
                if (descriptionContains("rain",description)){
                    imageView.setImageResource(R.drawable.rain);
                }
                if (descriptionContains("snow",description)){
                    imageView.setImageResource(R.drawable.snow);
                }
                if (descriptionContains("thunder",description)){
                    imageView.setImageResource(R.drawable.thunder);
                }
                if (descriptionContains("clear",description)){
                    imageView.setImageResource(R.drawable.clear);
                }
            }

        }else {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_first_item_row, parent, false);

                TextView city=(TextView) convertView.findViewById(R.id.today);
                TextView textNow=(TextView) convertView.findViewById(R.id.city);
                TextView current_Temp=(TextView) convertView.findViewById(R.id.currentTemp);
                TextView today=(TextView) convertView.findViewById(R.id.TextNow);
//                TextView dayHighText=(TextView) convertView.findViewById(R.id.DayHighText);
                TextView dayHighValue=(TextView) convertView.findViewById(R.id.DayHighValue);
//                TextView dayLowtext=(TextView) convertView.findViewById(R.id.DayLowText);
                TextView dayLowValue=(TextView) convertView.findViewById(R.id.DayLowValue);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.imgIcon);

                today.setText("Today");
                city.setText(currentTemp.city);
                current_Temp.setText(currentTemp.current_temp+"°");
                textNow.setText("Now");
//                dayHighText.setText("High:");
                dayHighValue.setText(data.high+"°");
//                dayLowtext.setText("Low:");
                dayLowValue.setText(data.low+"°");

                String description = data.description;



                if (descriptionContains("cloud",description)){
                    imageView.setImageResource(R.drawable.cloud_big);
                }
                if (descriptionContains("rain",description)){
                    imageView.setImageResource(R.drawable.rain_big);
                }
                if (descriptionContains("snow",description)){
                    imageView.setImageResource(R.drawable.snow_big);
                }
                if (descriptionContains("thunder",description)){
                    imageView.setImageResource(R.drawable.thunder_big);
                }
                if (descriptionContains("clear",description)){
                    imageView.setImageResource(R.drawable.clear_big);
                }


            }


        }





        return convertView;
    }

    private boolean descriptionContains(String str2,String description) {
        return description.toLowerCase().contains(str2.toLowerCase());
    }



}
