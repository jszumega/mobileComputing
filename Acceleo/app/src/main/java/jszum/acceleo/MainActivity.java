package jszum.acceleo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private AccelerometerListener acceleo;

    TextView textAcceleo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAcceleo = (TextView) findViewById(R.id.textAcc);

        Button scan = (Button) findViewById(R.id.button);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAccelerometer = scanAcceleo();

                if(isAccelerometer) textAcceleo.setText("Accelerometer: Present");
                else textAcceleo.setText("Accelerometer: Not found");
            }
        });
    }

    public boolean scanAcceleo() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            acceleo  =  new AccelerometerListener();
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(acceleo, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            return true;
        }
        else {
            return false;
        }
    }

    public class AccelerometerListener implements SensorEventListener {

        private float dX = 0.0f, dY = 0.0f, dZ = 0.0f;

        @Override
        public void onSensorChanged(SensorEvent event) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

    }
}
