package com.example.selvalgopgave;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private List<CityWeather> cityWeatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Opret en liste med eksempeldataværdier
        cityWeatherList = new ArrayList<>();
        cityWeatherList.add(new CityWeather("Copenhagen", "10°C", "Sunny", "5 m/s"));
        cityWeatherList.add(new CityWeather("Aarhus", "8°C", "Cloudy", "3 m/s"));

        // Sæt adapteren med kliklistener
        adapter = new WeatherAdapter(this, cityWeatherList, position -> {
            // Vis dialogboks for at slette byen
            showDeleteDialog(position);
        });
        recyclerView.setAdapter(adapter);

        // Tilføj funktion til at tilføje en by manuelt
        findViewById(R.id.btnAddCity).setOnClickListener(v -> showAddCityDialog());
    }

    private void showAddCityDialog() {
        // Vis dialogboks for at tilføje en ny by
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_add_city, null);
        builder.setView(dialogView);

        EditText cityNameInput = dialogView.findViewById(R.id.cityNameInput);
        EditText tempInput = dialogView.findViewById(R.id.tempInput);
        EditText conditionInput = dialogView.findViewById(R.id.conditionInput);
        EditText windSpeedInput = dialogView.findViewById(R.id.windSpeedInput);

        builder.setTitle("Add City")
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = cityNameInput.getText().toString();
                    String temperature = tempInput.getText().toString();
                    String condition = conditionInput.getText().toString();
                    String windSpeed = windSpeedInput.getText().toString();

                    // Valider input
                    if (!cityName.isEmpty() && !temperature.isEmpty() && !condition.isEmpty() && !windSpeed.isEmpty()) {
                        cityWeatherList.add(new CityWeather(cityName, temperature, condition, windSpeed));
                        adapter.notifyItemInserted(cityWeatherList.size() - 1);
                        Toast.makeText(this, "City added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteDialog(int position) {
        // Opret dialogboks
        new AlertDialog.Builder(this)
                .setTitle("Delete City")
                .setMessage("Are you sure you want to delete this city?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Fjern byen fra listen
                    cityWeatherList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(this, "City deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}