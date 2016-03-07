package com.example.android.sunshine.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import static android.support.v4.app.ActivityCompat.startActivity;


/**
 * Created by deepansh on 2/29/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    DayWeather[] data = null;
    CurrentWeather currentTemp;

    public CustomRecyclerViewAdapter(Context context, DayWeather[] data, CurrentWeather currentTemp){
        this.context=context;
        this.data= data;
        this.currentTemp= currentTemp;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewFirstItem=inflater.inflate(R.layout.listview_first_item_row, parent, false);
        View viewRestItems = inflater.inflate(R.layout.listview_item_row, parent, false);

        switch (viewType) {
            case 0: return new ViewHolder0(viewFirstItem);
            case 1: return new ViewHolder1(viewRestItems);
        }


        return new ViewHolder1(viewRestItems);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DayWeather item = data[position];
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailActivityIntent(item);

            }
        });
        int viewType = getItemViewType(position);
        if (viewType == 0) {
            ViewHolder0 viewHolder = (ViewHolder0) holder;

            viewHolder.today.setText("Today");
            viewHolder.city.setText(currentTemp.city);
            viewHolder.currentTemp.setText(currentTemp.current_temp + "°");
            viewHolder.textNow.setText("Now");
            viewHolder.dayHigh.setText(item.high+"°");
            viewHolder.dayLow.setText(item.low+"°");


            String description = item.description;

            if (descriptionContains("cloud",description)){
                viewHolder.imageView.setImageResource(R.drawable.cloud);
            }
            if (descriptionContains("rain",description)){
                viewHolder.imageView.setImageResource(R.drawable.rain);
            }
            if (descriptionContains("snow",description)){
                viewHolder.imageView.setImageResource(R.drawable.snow);
            }
            if (descriptionContains("thunder",description)){
                viewHolder.imageView.setImageResource(R.drawable.thunder);
            }
            if (descriptionContains("clear",description)){
                viewHolder.imageView.setImageResource(R.drawable.clear);
            }
        }
        if (viewType == 1) {
            ViewHolder1 viewHolder = (ViewHolder1) holder;

            viewHolder.dayDate.setText(item.day);
            viewHolder.dayDesc.setText(item.description );
            viewHolder.dayTempHigh.setText((item.high+"°"));
            viewHolder.dayTempLow.setText((item.low+"°"));

            String description = item.description;

            if (descriptionContains("cloud",description)){
                viewHolder.imageView.setImageResource(R.drawable.cloud);
            }
            if (descriptionContains("rain",description)){
                viewHolder.imageView.setImageResource(R.drawable.rain);
            }
            if (descriptionContains("snow",description)){
                viewHolder.imageView.setImageResource(R.drawable.snow);
            }
            if (descriptionContains("thunder",description)){
                viewHolder.imageView.setImageResource(R.drawable.thunder);
            }
            if (descriptionContains("clear",description)){
                viewHolder.imageView.setImageResource(R.drawable.clear);
            }
        }
    }

    private void startDetailActivityIntent(DayWeather item) {
//        DayWeather forecast = item;

//        Bundle bundle=new Bundle();
//        bundle.pu("DayWeatherResults", data);
//        bundle.putSerializable("CurrentTempResults",  currentTemp);

        Intent intent;
        intent = new Intent(context, DetailActivity.class);
        intent.putExtra("www",data);
//        bundle.putSerializable("CurrentTemps",  currentTemp);
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        else {
            return 1;
        }
    }

    class ViewHolder0 extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView dayLow;
        private final TextView dayHigh;
        private final TextView textNow;
        private final TextView currentTemp;
        private final TextView city;
        private final TextView today;

        public ViewHolder0(View convertView) {
            super(convertView);

            today = (TextView) convertView.findViewById(R.id.today);
            city = (TextView) convertView.findViewById(R.id.city);
            currentTemp = (TextView) convertView.findViewById(R.id.currentTemp);
            textNow = (TextView) convertView.findViewById(R.id.TextNow);
            dayHigh = (TextView) convertView.findViewById(R.id.DayHighValue);
            dayLow = (TextView) convertView.findViewById(R.id.DayLowValue);

            imageView = (ImageView) convertView.findViewById(R.id.imgIcon);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView dayDate;
        private final TextView dayDesc;
        private final TextView dayTempHigh;
        private final TextView dayTempLow;

        public ViewHolder1(View convertView) {
            super(convertView);

            imageView = (ImageView) convertView.findViewById(R.id.imgIcon);
            dayDate = (TextView) convertView.findViewById(R.id.dayDate);
            dayDesc = (TextView) convertView.findViewById(R.id.dayDesc);
            dayTempHigh = (TextView) convertView.findViewById(R.id.dayTempHigh);
            dayTempLow = (TextView) convertView.findViewById(R.id.dayTempLow);
        }
    }
    private boolean descriptionContains(String str2,String description) {
        return description.toLowerCase().contains(str2.toLowerCase());
    }



//    public int getItem(int position) {
//        return messageList.get(position);
//    }

}
