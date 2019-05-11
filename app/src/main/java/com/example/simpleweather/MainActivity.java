package com.example.simpleweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_SETTINGS = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.places_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final FirstRecycleAdapter adapter = new FirstRecycleAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FirstRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                WeatherInformer weatherInformer=WeatherInformer.getInstance();
                String[] places = weatherInformer.getPlaces();
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("Place",places[position]);
                startActivity(intent);
            }
        });

        Button add = findViewById(R.id.add_new_place);
        final TextView new_place = findViewById(R.id.new_place);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new_place.getText().length() > 0) {
                    WeatherInformer weatherInformer = WeatherInformer.getInstance();
                    weatherInformer.addPlace(new_place.getText().toString());
                    adapter.refreshData();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            WeatherInformer weatherInformer = WeatherInformer.getInstance();
            intent.putExtra("Pressure", weatherInformer.isShowPressure());
            intent.putExtra("Humidity", weatherInformer.isShowHumidity());
            intent.putExtra("Wind", weatherInformer.isShowWind());
            intent.putExtra("Precipitation", weatherInformer.isShowPrecipitation());
            startActivityForResult(intent, REQUEST_SETTINGS);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            WeatherInformer weatherInformer = WeatherInformer.getInstance();
            weatherInformer.setShowHumidity(data.getBooleanExtra("Humidity", false));
            weatherInformer.setShowPressure(data.getBooleanExtra("Pressure", false));
            weatherInformer.setShowWind(data.getBooleanExtra("Wind", false));
            weatherInformer.setShowPrecipitation(data.getBooleanExtra("Precipitation", false));
        }
    }
}

