package com.example.android.sunshine.app;

import com.example.android.sunshine.app.Fragment.ForecastFragment;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by deepansh on 2/26/16.
 */
public class ForecastFragmentTest {

    private ForecastFragment fragment;

    @Before
    public void setup(){
        fragment = new ForecastFragment();

    }
    @Test
    public void testFormatWeather(){
        String[] value = fragment.formatHighLows(100,0,"imperial", "imperial");
        assertEquals(2, value.length);
        assertEquals("212", value[0]);
        assertEquals("32", value[1]);
    }
}
