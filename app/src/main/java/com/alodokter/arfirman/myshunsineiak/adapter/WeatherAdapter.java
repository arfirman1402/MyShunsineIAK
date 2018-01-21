package com.alodokter.arfirman.myshunsineiak.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alodokter.arfirman.myshunsineiak.App;
import com.alodokter.arfirman.myshunsineiak.R;
import com.alodokter.arfirman.myshunsineiak.activity.DetailWeatherActivity;
import com.alodokter.arfirman.myshunsineiak.model.Forecast;
import com.alodokter.arfirman.myshunsineiak.viewholder.WeatherViewHolder;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alodokter-arfirman on 14/01/18.
 */

public class WeatherAdapter extends RecyclerView.Adapter implements WeatherViewHolder.WeatherCallback {
    List<Forecast> forecasts;

    public WeatherAdapter() {
        forecasts = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(WeatherViewHolder.getWeatherLayout(), parent, false);
        return new WeatherViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setWeatherItem((WeatherViewHolder) holder);

    }

    private void setWeatherItem(WeatherViewHolder holder) {
        Forecast forecast = forecasts.get(holder.getAdapterPosition());
        Glide.with(holder.itemView.getContext()).load(getWeatherImageUrl(forecast.getWeatherList().get(0).getWeatherIcon())).into(holder.getWeatherImage());
        Date date = new Date(forecast.getForecastDate() * 1000);
        String datePattern = "EEE, MMM dd";
        SimpleDateFormat outputFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
        String strDate = outputFormat.format(date);
        holder.getWeatherDate().setText(strDate);
        holder.getWeatherDesc().setText(forecast.getWeatherList().get(0).getWeatherDesc());
        long tempDay = Math.round(forecast.getTemperature().getTempDay());
        holder.getWeatherTemp().setText(tempDay + holder.itemView.getContext().getString(R.string.degree));

    }

    private String getWeatherImageUrl(String weatherIcon) {
        return "http://openweathermap.org/img/w/" + weatherIcon + ".png";
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public void setData(List<Forecast> forecasts) {
        this.forecasts.addAll(forecasts);
        notifyDataSetChanged();
    }

    @Override
    public void onWeatherClick(WeatherViewHolder holder) {
        Intent intent = new Intent(holder.itemView.getContext(), DetailWeatherActivity.class);
        String forecastJson = App.getInstance().getGson().toJson(forecasts.get(holder.getAdapterPosition()));
        intent.putExtra("forecast", forecastJson);
        holder.itemView.getContext().startActivity(intent);
    }
}