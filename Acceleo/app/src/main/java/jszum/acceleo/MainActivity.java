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
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static int AXIS_X = 0;
    final static int AXIS_Y = 1;
    final static int AXIS_Z = 2;

    private float dX = 0.0f, dY = 0.0f, dZ = 0.0f;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private AccelerometerListener acceleo;

    TextView textAcceleo;
    EditText textX, textY, textZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAcceleo = (TextView) findViewById(R.id.textAcc);
        textX = (EditText) findViewById(R.id.editTextX);
        textX.setFocusable(false);
        textY = (EditText) findViewById(R.id.editTextY);
        textY.setFocusable(false);
        textZ = (EditText) findViewById(R.id.editTextZ);
        textZ.setFocusable(false);

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

    public void update_Accelerometer() {
        textX.setText(Float.toString(dX));
        textY.setText(Float.toString(dY));
        textZ.setText(Float.toString(dZ));
    }

    public class AccelerometerListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                dX = event.values[AXIS_X];
                dY = event.values[AXIS_Y];
                dZ = event.values[AXIS_Z];
            }
            update_Accelerometer();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
