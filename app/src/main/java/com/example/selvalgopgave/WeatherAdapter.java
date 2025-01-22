package com.example.selvalgopgave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<CityWeather> cityWeatherList;
    private Context context;
    private OnCityClickListener listener; // Kliklistener

    public interface OnCityClickListener {
        void onCityClick(int position); // Klikfunktion for at slette byer
    }

    public WeatherAdapter(Context context, List<CityWeather> cityWeatherList, OnCityClickListener listener) {
        this.cityWeatherList = cityWeatherList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        return new WeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        CityWeather cityWeather = cityWeatherList.get(position);
        holder.cityName.setText(cityWeather.getCityName());
        holder.temperature.setText("Temp: " + cityWeather.getTemperature());
        holder.conditions.setText("Conditions: " + cityWeather.getConditions());
        holder.windSpeed.setText("Wind Speed: " + cityWeather.getWindSpeed());

        // Sæt kliklistener for at slette byer
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCityClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityWeatherList.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView cityName, temperature, conditions, windSpeed;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            temperature = itemView.findViewById(R.id.temperature);
            conditions = itemView.findViewById(R.id.conditions);
            windSpeed = itemView.findViewById(R.id.windSpeed);
        }
    }
}