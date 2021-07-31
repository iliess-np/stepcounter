package com.iliessnp.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_stepsCounter, tv_stepsDetector;
    SensorManager sensorManager;
    int reportedSteps = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_stepsCounter = findViewById(R.id.tv_stepsCounter);
        tv_stepsDetector = findViewById(R.id.tv_stepsDetector);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Sensor countSensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
        if (countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
            //Toast.makeText(this, "am here", Toast.LENGTH_SHORT).show();

        }else  {
            Toast.makeText(this, "Sensor Not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            reportedSteps = (int)event.values [0];
            Toast.makeText(this, "its runing"+ reportedSteps, Toast.LENGTH_SHORT).show();
            tv_stepsCounter.setText(String.valueOf(reportedSteps));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}