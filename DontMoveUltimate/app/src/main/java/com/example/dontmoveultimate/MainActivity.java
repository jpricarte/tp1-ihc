package com.example.dontmoveultimate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private EditText xAxleText;
    private EditText yAxleText;
    private EditText zAxleText;
    boolean shouldWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        xAxleText = findViewById(R.id.xAxleText);
        yAxleText = findViewById(R.id.yAxleText);
        zAxleText = findViewById(R.id.zAxleText);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
        shouldWarn = true;
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
        shouldWarn = false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            double sensorX = sensorEvent.values[0];
            double sensorY = sensorEvent.values[1];
            double sensorZ = sensorEvent.values[2];
            double total = Math.sqrt(Math.pow(sensorX,2) + Math.pow(sensorY,2) + Math.pow(sensorZ,2));

            xAxleText.setText("X: " + sensorX);
            yAxleText.setText("Y: " + sensorY);
            zAxleText.setText("Z: " + sensorZ);

            if (shouldWarn && total > 13) {
                shouldWarn = false;
                Intent tooFastActivity = new Intent(this, TooFastActivity.class);
                startActivity(tooFastActivity);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}