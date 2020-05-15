package com.example.aapplication.weather;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aapplication.DiscreteScrollView;
import com.example.aapplication.DiscreteScrollViewOptions;
import com.example.aapplication.R;
import com.example.aapplication.transform.ScaleTransformer;

import java.util.List;

public class WeatherActivity extends AppCompatActivity implements
        DiscreteScrollView.ScrollStateChangeListener<ForecastAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<ForecastAdapter.ViewHolder>,
        View.OnClickListener {

private List<Forecast> forecasts;

private ForecastView forecastView;
private DiscreteScrollView cityPicker;

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        forecastView = (ForecastView) findViewById(R.id.forecast_view);

        forecasts = WeatherStation.get().getForecasts();
        cityPicker = (DiscreteScrollView) findViewById(R.id.forecast_city_picker);
        cityPicker.setSlideOnFling(true);
        cityPicker.setAdapter(new ForecastAdapter(forecasts));
        cityPicker.addOnItemChangedListener(this);
        cityPicker.addScrollStateChangeListener(this);
        cityPicker.scrollToPosition(2);
        //cityPicker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        cityPicker.setItemTransformer(new ScaleTransformer.Builder()
        .setMinScale(0.8f)
        .build());

        forecastView.setForecast(forecasts.get(0));

        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.btn_spot).setOnClickListener(this);
        findViewById(R.id.btn_details).setOnClickListener(this);
        }

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@Override
public void onCurrentItemChanged(@Nullable ForecastAdapter.ViewHolder holder, int position) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (holder != null) {
        forecastView.setForecast(forecasts.get(position));
        holder.showText();
        }
        }

@Override
public void onScrollStart(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        holder.hideText();
        }

@Override
public void onScroll(
        float position,
        int currentIndex, int newIndex,
@Nullable ForecastAdapter.ViewHolder currentHolder,
@Nullable ForecastAdapter.ViewHolder newHolder) {
        Forecast current = forecasts.get(currentIndex);
        if (newIndex >= 0 && newIndex < cityPicker.getAdapter().getItemCount()) {
        Forecast next = forecasts.get(newIndex);
        forecastView.onScroll(1f - Math.abs(position), current, next);
        }
        }

@Override
public void onClick(View v) {
        switch (v.getId()) {
        case R.id.home:
        finish();
        break;
        case R.id.btn_details:
        DiscreteScrollViewOptions.configureTransitionTime(cityPicker);
        break;
        case R.id.btn_spot:
        DiscreteScrollViewOptions.smoothScrollToUserSelectedPosition(cityPicker, v);
        break;
        }
        }

@Override
public void onScrollEnd(@NonNull ForecastAdapter.ViewHolder holder, int position) {

        }
        }