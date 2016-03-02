package com.example.android.sunshine.app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by deepansh on 3/1/16.
 */
public class CustomViewPagerAdapter extends PagerAdapter{
    private Context mContext;
    private DayWeather[] item;
    private CurrentWeather currentTemp;

    public CustomViewPagerAdapter(Context context,DayWeather[] dayWeather,CurrentWeather currentTemp) {
        mContext=context;
        this.item =dayWeather;
        this.currentTemp = currentTemp;


    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup convertView = null;

        if (position==0) {
            convertView = (ViewGroup) inflater.inflate(R.layout.view_pager_item_layout, collection, false);

            final DayWeather daywthr = item[position];
            int viewType = getItemViewType(position);

            TextView dayDate = (TextView) convertView.findViewById(R.id.day);
            TextView current_temp = (TextView) convertView.findViewById(R.id.currentTemp);
            TextView dayHighLowText = (TextView) convertView.findViewById(R.id.dayHighLowText);
            TextView dayHighLowVlue = (TextView) convertView.findViewById(R.id.dayHighLowValue);

            TextView pressureText =(TextView) convertView.findViewById(R.id.pressureText);
            TextView pressureValue = (TextView) convertView.findViewById(R.id.pressureValue);

            TextView humidityText = (TextView) convertView.findViewById(R.id.humidityText);
            TextView humidityValue = (TextView) convertView.findViewById(R.id.humidityValue);

            dayDate.setText("Today");
            current_temp.setText(currentTemp.current_temp);
            dayHighLowText.setText("Day High/Low");
            dayHighLowVlue.setText(daywthr.high+"/"+daywthr.low);
            pressureText.setText("Pressure");
            pressureValue.setText(daywthr.pressure);
            humidityText.setText("Humidity");
            humidityValue.setText(daywthr.humidity);

            collection.addView(convertView);
        }
        else {
            convertView = (ViewGroup) inflater.inflate(R.layout.view_pager_item_layout, collection, false);

            final DayWeather daywthr = item[position];
            int viewType = getItemViewType(position);

            TextView dayDate = (TextView) convertView.findViewById(R.id.day);
            TextView current_temp = (TextView) convertView.findViewById(R.id.currentTemp);
            TextView dayHighLowText = (TextView) convertView.findViewById(R.id.dayHighLowText);
            TextView dayHighLowVlue = (TextView) convertView.findViewById(R.id.dayHighLowValue);

            TextView pressureText =(TextView) convertView.findViewById(R.id.pressureText);
            TextView pressureValue = (TextView) convertView.findViewById(R.id.pressureValue);

            TextView humidityText = (TextView) convertView.findViewById(R.id.humidityText);
            TextView humidityValue = (TextView) convertView.findViewById(R.id.humidityValue);

            dayDate.setText(daywthr.day);
            current_temp.setText(daywthr.dayAverage);
            dayHighLowText.setText("Day High/Low");
            dayHighLowVlue.setText(daywthr.high+"/"+daywthr.low);
            pressureText.setText("Pressure");
            pressureValue.setText(daywthr.pressure);
            humidityText.setText("Humidity");
            humidityValue.setText(daywthr.humidity);

            collection.addView(convertView);
        }

        return convertView;

    }

    private int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        else {
            return 1;
        }    }

    @Override
    public int getCount() {
        return item.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);

    }
//    instantiateItem(ViewGroup viewgroup,int position)
}
