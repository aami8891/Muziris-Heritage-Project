package com.example.aapplication.weather;

import com.example.aapplication.R;

import java.util.Arrays;
import java.util.List;

public class WeatherStation {


    public static WeatherStation get() {
        return new WeatherStation();
    }

    private WeatherStation() {
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(
                new Forecast("Ernakulam", R.drawable.pisa/*pisa*/, "Kerala History Museum", Weather.PARTLY_CLOUDY),
                new Forecast("Thiruvananthapuram", R.drawable.paris/*paris*/, "Napier Museum", Weather.CLEAR),
                new Forecast("Thrissur", R.drawable.pisa/*new_york*/, "Shakthan Thampuran Palace", Weather.MOSTLY_CLOUDY),
                new Forecast("Tripunithura", R.drawable.paris/*rome*/, "Hill Palace", Weather.PARTLY_CLOUDY),
                new Forecast("Kayamkulam", R.drawable.washington/*london*/, "Krishnapuram Palace", Weather.PERIODIC_CLOUDS),
                new Forecast("Kodungallur", R.drawable.paris, "Cheraman Juma Mosque", Weather.CLEAR));
    }
}
