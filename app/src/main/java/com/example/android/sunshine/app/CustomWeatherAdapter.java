package com.example.android.sunshine.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by deepanshpahwa on 2/19/16.
 */
public class CustomWeatherAdapter extends ArrayAdapter<Weather> {
    Context context;
    int layoutResourceID;
    List<Weather> data = null;

    public CustomWeatherAdapter(Context context, int resource, List<Weather> data) {
        super(context, resource, data);
        this.context=context;
//        this.layoutResourceID=layoutResourceID;
        this.data= data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row=convertView;
        WeatherHolder holder;

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceID,parent,false);

            holder = new WeatherHolder();
            holder.icon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }
        Weather weather = data.get(position);
        holder.txtTitle.setText(weather.main.);
        holder.icon.setImageResource(weather.icon);

        return row;
    }
    public static class WeatherHolder{
        ImageView icon;
        TextView txtTitle;
    }

}
