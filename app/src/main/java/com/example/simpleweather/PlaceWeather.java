package com.example.simpleweather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TreeMap;

public class PlaceWeather {
    private TreeMap<String, WeatherData> placeInfo = new TreeMap<>();
    private String place;
    private String actualTemp;
    private String initDate;
    private String fullDate;
    private String[] hystoryData = new String[10];
    private String[] hystoryTemp = new String[10];
    private int[] hystoryPrecip = new int[10];

    public PlaceWeather(String place, Calendar calendar) {
        Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTime(calendar.getTime());
        this.place = place;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM");
        SimpleDateFormat keyFormat = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat fullFormat = new SimpleDateFormat("d MMMM yyyy, EEEE");
        initDate = keyFormat.format(mCalendar.getTime());
        fullDate=fullFormat.format(calendar.getTime());
        placeInfo.put(keyFormat.format(mCalendar.getTime()), new WeatherData(dateFormat.format(mCalendar.getTime())));
        for (int j = 0; j < 10; j++) {
            mCalendar.add(Calendar.DATE, -1);
            WeatherData weatherData = new WeatherData(dateFormat.format(mCalendar.getTime()));
            placeInfo.put(keyFormat.format(mCalendar.getTime()), weatherData);
            hystoryData[9 - j] = weatherData.getDate();
            hystoryTemp[9 - j] = weatherData.getTemperature();
            hystoryPrecip[9 - j] = Integer.parseInt(weatherData.getPrecipitation());
        }
        actualTemp = placeInfo.get(initDate).getTemperature();
    }

    public String getPlace() { return place; }

    public String getActualTemp() { return actualTemp; }

    public TreeMap<String, WeatherData> getPlaceInfo() { return placeInfo; }

    public String getPressure() { return placeInfo.get(initDate).getPressure(); }

    public String getHumidity() { return placeInfo.get(initDate).getHumidity(); }

    public String getWindDirection() { return placeInfo.get(initDate).getWindDirection(); }

    public String getWindForce() { return placeInfo.get(initDate).getWindForce(); }

    public String getPrecipitation() { return placeInfo.get(initDate).getPrecipitation(); }

    public String getInitDate() { return fullDate; }

    public String[] getDatas() { return hystoryData; }

    public String[] getTemps() { return hystoryTemp; }

    public int[] getPrecip() { return hystoryPrecip; }
}
