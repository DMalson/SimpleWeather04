package com.example.simpleweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final CheckBox cbPressure = findViewById(R.id.cbPressure);
        final CheckBox cbHumidity = findViewById(R.id.cbHumidity);
        final CheckBox cbWind = findViewById(R.id.cbWind);
        final CheckBox cbPrecipitation = findViewById(R.id.cbPrecipitation);

        //Учебное применение startActivityForResult с передачей параметров через Intent
        Intent intent = getIntent();
        cbPressure.setChecked(intent.getBooleanExtra("Pressure", false));
        cbHumidity.setChecked(intent.getBooleanExtra("Humidity", false));
        cbWind.setChecked(intent.getBooleanExtra("Wind", false));
        cbPrecipitation.setChecked(intent.getBooleanExtra("Precipitation", false));

        Button okButton = findViewById(R.id.btnOK);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Учебное применение startActivityForResult с передачей параметров через Intent
                intent.putExtra("Pressure", cbPressure.isChecked());
                intent.putExtra("Humidity", cbHumidity.isChecked());
                intent.putExtra("Wind", cbWind.isChecked());
                intent.putExtra("Precipitation", cbPrecipitation.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}


