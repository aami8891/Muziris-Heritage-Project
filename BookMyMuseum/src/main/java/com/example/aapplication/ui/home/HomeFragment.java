package com.example.aapplication.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.aapplication.DiscreteScrollView;
import com.example.aapplication.DiscreteScrollViewOptions;
import com.example.aapplication.R;
import com.example.aapplication.transform.ScaleTransformer;
import com.example.aapplication.weather.Forecast;
import com.example.aapplication.weather.ForecastAdapter;
import com.example.aapplication.weather.ForecastView;
import com.example.aapplication.weather.WeatherStation;

import java.util.List;

public class HomeFragment extends Fragment implements DiscreteScrollView.ScrollStateChangeListener<ForecastAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<ForecastAdapter.ViewHolder>,
        View.OnClickListener {

    private HomeViewModel homeViewModel;
    private List<Forecast> forecasts;

    private ForecastView forecastView;
    private DiscreteScrollView cityPicker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        forecastView = (ForecastView)root.findViewById(R.id.forecast_view);

        forecasts = WeatherStation.get().getForecasts();
        cityPicker = (DiscreteScrollView)root. findViewById(R.id.forecast_city_picker);
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

//        root.findViewById(R.id.home).setOnClickListener(this);
       root.findViewById(R.id.btn_details).setOnClickListener(this);
      root. findViewById(R.id.btn_spot).setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.home:
               getActivity().finish();
                break;*/
            case R.id.btn_spot:
                DiscreteScrollViewOptions.configureTransitionTime(cityPicker);
                break;
            case R.id.btn_details:
                DiscreteScrollViewOptions.smoothScrollToUserSelectedPosition(cityPicker, v);
                break;
        }
    }


    @Override
    public void onScrollStart(@NonNull ForecastAdapter.ViewHolder holder, int adapterPosition) {
        holder.hideText();
    }

    @Override
    public void onScrollEnd(@NonNull ForecastAdapter.ViewHolder currentItemHolder, int adapterPosition) {

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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCurrentItemChanged(@Nullable ForecastAdapter.ViewHolder holder, int position) {
        if (holder != null) {
            forecastView.setForecast(forecasts.get(position));
            holder.showText();
        }
    }
}