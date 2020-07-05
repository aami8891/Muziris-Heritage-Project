package com.yarolegovich.discretescrollview.sample.weather;

import com.yarolegovich.discretescrollview.sample.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class WeatherStation {


    public static WeatherStation get() {
        return new WeatherStation();
    }

    private WeatherStation() {
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(
                new Forecast("Museum 1", R.drawable.pisa, "NAPIER MUSEUM, THIRUVANANTHAPURAM", Weather.CLOUDY),
                new Forecast("Museum 2", R.drawable.paris, "MUSEUM OF KERALA ARTS AND HISTORY, KOCHI", Weather.CLEAR),
                new Forecast("Museum 3", R.drawable.new_york, "ARCHAEOLOGICAL MUSEUM THRISSUR", Weather.MOSTLY_CLOUDY),
                new Forecast("Museum 4", R.drawable.rome, "KUTHIRAMALIKA PALACE MUSEUM, THIRUVANANTHAPURAM", Weather.PARTLY_CLOUDY),
                new Forecast("Museum 5", R.drawable.london, "HILL PALACE", Weather.PERIODIC_CLOUDS),
                new Forecast("MAP", R.drawable.washington, "MUSEUM OF KERALA ARTS AND HISTORY, KOCHI", Weather.CLEAR));
    }
}
