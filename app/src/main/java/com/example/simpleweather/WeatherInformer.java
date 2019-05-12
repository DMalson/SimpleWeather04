package com.example.simpleweather;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

public class WeatherInformer {
    private static WeatherInformer wiInstance;
    private static String[] initPlaces = {"MOSCOW", "NEW YORK", "PARIS", "LONDON", "BEJING", "DELI", "ROME", "SAINT PETERSBURG",
            "BERLIN", "UFA", "TOKYO", "ANKARA", "PRAGUE", "SAMARA", "DAVLEKANOVO", "WARSAW", "KIEV", "MINSK", "ASTANA"};
    private static TreeMap<String, PlaceWeather> weatherMap = new TreeMap<>();
    private static Calendar initCalendar;
    private static boolean showPressure = true;
    private static boolean showHumidity = true;
    private static boolean showWind = true;
    private static boolean showPrecipitation = true;

    private WeatherInformer() {
        initWeatherInfo();
    }

    public static WeatherInformer getInstance() {
        if (wiInstance == null) {
            wiInstance = new WeatherInformer();
        }
        return wiInstance;
    }

    private void initWeatherInfo() {
        initCalendar = Calendar.getInstance();
        initCalendar.setTime(new Date());
        for (int i = 0; i < initPlaces.length; i++) {
            PlaceWeather place_info = new PlaceWeather(initPlaces[i], initCalendar);
            weatherMap.put(initPlaces[i], place_info);
        }
    }

    public String[] getPlaces() {
        String[] places = new String[weatherMap.size()];
        int i = 0;
        for (TreeMap.Entry entry : weatherMap.entrySet()) {
            places[i] = (String) entry.getKey();
            i++;
        }
        return places;
    }

    public String[] getTemps() {
        String[] temps = new String[weatherMap.size()];
        int i = 0;
        for (TreeMap.Entry entry : weatherMap.entrySet()) {
            temps[i] = ((PlaceWeather) entry.getValue()).getActualTemp();
            i++;
        }
        return temps;
    }

    public void addPlace(String place) {
        String myPlace = place.toUpperCase();
        if (!weatherMap.containsKey(myPlace)) {
            PlaceWeather place_info = new PlaceWeather(myPlace, initCalendar);
            weatherMap.put(myPlace, place_info);
        }
    }

    public boolean isShowPressure() {
        return showPressure;
    }

    public void setShowPressure(boolean showPressure) {
        WeatherInformer.showPressure = showPressure;
    }

    public boolean isShowHumidity() {
        return showHumidity;
    }

    public void setShowHumidity(boolean showHumidity) {
        WeatherInformer.showHumidity = showHumidity;
    }

    public boolean isShowWind() {
        return showWind;
    }

    public void setShowWind(boolean showWind) {
        WeatherInformer.showWind = showWind;
    }

    public boolean isShowPrecipitation() {
        return showPrecipitation;
    }

    public void setShowPrecipitation(boolean showPrecipitation) {
        WeatherInformer.showPrecipitation = showPrecipitation;
    }

    public PlaceWeather getPlaceWeather(String place) {
        if (weatherMap.containsKey(place)) {
            return weatherMap.get(place);
        } else {
            return weatherMap.get("MOSCOW");
        }
    }

    public void getPreferences(Context applicationContext) {
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
        showHumidity = sharedPreferences.getBoolean("Humidity", true);
        showPrecipitation = sharedPreferences.getBoolean("Precipitation", true);
        showPressure = sharedPreferences.getBoolean("Pressure", true);
        showWind = sharedPreferences.getBoolean("Wind", true);
    }
    public void setPreferences(Context applicationContext) {
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putBoolean("Humidity", showHumidity);
        e.putBoolean("Precipitation", showPrecipitation);
        e.putBoolean("Pressure", showPressure);
        e.putBoolean("Wind", showWind);
        e.apply();
    }
}

