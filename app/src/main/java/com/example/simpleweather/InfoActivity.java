package com.example.simpleweather;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    private Resources resources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_weather_info);
        resources = getResources();
        //
        TextView pw_place = findViewById(R.id.pw_place);
        TextView pw_date = findViewById(R.id.pw_date);
        TextView pw_pressure = findViewById(R.id.textPressure);
        TextView pw_humidity = findViewById(R.id.textHumidity);
        TextView pw_wind = findViewById(R.id.textWind);
        TextView pw_temp = findViewById(R.id.pw_Temp);
        ImageView pw_precipitation = findViewById(R.id.pw_Precipitation);
        TextView pw_precipText = findViewById(R.id.pw_PrecipText);

        WeatherInformer weatherInformer = WeatherInformer.getInstance();
        PlaceWeather placeWeather = weatherInformer.getPlaceWeather(getIntent().getStringExtra("Place"));
        String[] precipDescriptions = resources.getStringArray(R.array.precipitation);
        String[] windDirections=resources.getStringArray(R.array.windDirection);
        int[] precipPictures = getImageArray();
        int precipIndex = Integer.parseInt(placeWeather.getPrecipitation());

        pw_place.setText(placeWeather.getPlace());
        pw_date.setText(placeWeather.getInitDate());
        pw_pressure.setText(getString(R.string.txt_pressure, placeWeather.getPressure()));
        pw_humidity.setText(getString(R.string.txt_humidity, placeWeather.getHumidity()));
        String windDirection=windDirections[Integer.parseInt(placeWeather.getWindDirection())/45];
        pw_wind.setText(getString(R.string.txt_wind, windDirection, placeWeather.getWindForce()));
        pw_temp.setText(placeWeather.getActualTemp());
        pw_precipText.setText(precipDescriptions[precipIndex]);
        pw_precipitation.setImageResource(precipPictures[precipIndex]);

        if (!weatherInformer.isShowHumidity()) pw_humidity.setVisibility(View.GONE);
        if (!weatherInformer.isShowPressure()) pw_pressure.setVisibility(View.GONE);
        if (!weatherInformer.isShowWind()) pw_wind.setVisibility(View.GONE);
        if (!weatherInformer.isShowPrecipitation()){
            pw_precipitation.setVisibility(View.GONE);
            pw_precipText.setVisibility(View.GONE);
        }

        RecyclerView recyclerView = findViewById(R.id.weatherHistory);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        final SecondRecycleAdapter adapter = new SecondRecycleAdapter(placeWeather.getPlace(),precipPictures);
        recyclerView.setAdapter(adapter);
    }

    // Механизм вытаскивания идентификаторов картинок (к сожеланию просто массив не работает)
    // https://stackoverflow.com/questions/5347107/creating-integer-array-of-resource-ids
    private int[] getImageArray() {
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++)
            answer[i] = pictures.getResourceId(i, 0);
        pictures.recycle();
        return answer;
    }
}
