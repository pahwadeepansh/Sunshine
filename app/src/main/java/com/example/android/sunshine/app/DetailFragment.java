package com.example.android.sunshine.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by deepansh on 3/7/16.
 */
public class DetailFragment extends Fragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
            private DayWeather data;
//    CurrentWeather currentTemp;
    ArrayList<DayWeather> result= new ArrayList<DayWeather>();
    ViewPager viewpager;
    CurrentWeather currentWeatherObject= new CurrentWeather();

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container1,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container1, false);
        viewpager = (ViewPager) rootView.findViewById(R.id.DetailActivityViewPager);

        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
        if (intent != null) {
            result = intent.getParcelableArrayListExtra("www");
        }

        currentWeatherObject.current_temp="234";
        currentWeatherObject.city="Chicago";
        viewpager.setAdapter(new CustomViewPagerAdapter(getContext(),result,currentWeatherObject));

        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detailfragment, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
//            shareIntent.putExtra(Intent.EXTRA_TEXT,
//                    mForecastStr + FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }
}
