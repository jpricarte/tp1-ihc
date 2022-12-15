package com.example.sensorplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private Sensor magneticSensor;
    private TextView lightInfoText;
    private TextView magneticValueText;
    private TextView gpsInfoText;
    private Button gpsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightInfoText = findViewById(R.id.lightInfoText);
        magneticValueText = findViewById(R.id.magneticValueText);
        gpsInfoText = findViewById(R.id.gpsInfoText);
        gpsButton = findViewById(R.id.gpsButton);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Initialize Light Sensor
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {
            sensorManager.registerListener(MainActivity.this, lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightInfoText.setText("Light Sensor not supported");
        }

        // Initialize Temperature Sensor
        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticSensor != null) {
            sensorManager.registerListener(MainActivity.this, magneticSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            magneticValueText.setText("Magnetic Field Sensor not supported");
        }

        // Initialize GPS (request permission)
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        // Get GPS info pressing button
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    double alt = l.getAltitude();
                    gpsInfoText.setText("Lat: " + lat + "\nLon: " + lon + "\nAlt: " + alt);
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_LIGHT) {
            lightInfoText.setText("Light Intensity: " + sensorEvent.values[0]);
        }
        if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticValueText.setText("MagField: " + sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}