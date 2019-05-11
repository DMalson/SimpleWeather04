package com.example.simpleweather;

import java.util.Random;

public class WeatherData {
    private String date;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windForce;
    private String windDirection;
    private String precipitation;

    public WeatherData(String date) {
        this.date = date;
        Random random = new Random();
        int temp = random.nextInt(50) - 20;
        temperature = (temp > 0 ? "+" + temp : "" + temp)  + "\u00B0C";
        pressure = Integer.toString(random.nextInt(50) + 730);
        humidity = ""+(random.nextInt(20) + 80) + "%";
        int wind = random.nextInt(15);
        windForce = "" + wind + "-" + (wind + random.nextInt(5));
        windDirection = Integer.toString(random.nextInt(7) * 45);
        precipitation = Integer.toString(random.nextInt(11));
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindForce() {
        return windForce;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getPrecipitation() {
        return precipitation;
    }
}
