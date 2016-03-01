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
    private DayWeather[] data;

    public CustomViewPagerAdapter(Context context,DayWeather[] dayWeather) {
        mContext=context;
        this.data =dayWeather;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup convertView = (ViewGroup) inflater.inflate(R.layout.listview_item_row, collection, false);

        final DayWeather item = data[position];
        int viewType = getItemViewType(position);



        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgIcon);
        TextView dayDate = (TextView) convertView.findViewById(R.id.dayDate);
        TextView dayDesc = (TextView) convertView.findViewById(R.id.dayDesc);
        TextView dayTempHigh = (TextView) convertView.findViewById(R.id.dayTempHigh);
        TextView dayTempLow = (TextView) convertView.findViewById(R.id.dayTempLow);

        dayDate.setText(item.day);
        dayDesc.setText(item.description );
        dayTempHigh.setText((item.high+"°"));
        dayTempLow.setText((item.low+"°"));

        collection.addView(convertView);

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
        return data.length;
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
