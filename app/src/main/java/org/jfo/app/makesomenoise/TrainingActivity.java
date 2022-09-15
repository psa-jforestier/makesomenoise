package org.jfo.app.makesomenoise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TrainingActivity extends AppCompatActivity {

    private MainActivity.WhipDetector whipDetector;
    public int maxValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        whipDetector = new MainActivity.WhipDetector(this, 0,new MainActivity.WhipDetector.Callback() {
            @Override
            public void whipNao(int sensorValue) {

                TextView textView = (TextView)findViewById(R.id.txtSensorValue);
                textView.setText("sensor value = " + sensorValue + " / Max = " + maxValue);
                if (sensorValue > maxValue)
                {
                    maxValue = sensorValue;
                }
            }
        });
        whipDetector.on();
    }

}