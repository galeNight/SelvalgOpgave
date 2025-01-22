package com.example.selvalgopgave;

public class CityWeather {
    private String cityName;
    private String temperature;
    private String conditions;
    private String windSpeed;

    // Constructor
    public CityWeather(String cityName, String temperature, String conditions, String windSpeed) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.conditions = conditions;
        this.windSpeed = windSpeed;
    }
    // Getters
    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getConditions() {
        return conditions;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
