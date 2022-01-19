package org.jfo.app.makesomenoise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private WhipDetector whipDetector;
    //Button bt;
    MediaPlayer mpLastSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bt = (Button)findViewById(R.id.bTada);
        // Ordered by buttons on the UI
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.whip);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.drama);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.laugh);
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.badjoke);
        final MediaPlayer mp5 = MediaPlayer.create(this, R.raw.tada);

        mpLastSound = mp1;
        /**
        ((Button)findViewById(R.id.bWhip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp1.start();
                mpLastSound = mp1;
            }
        });
         **/
        ((Button)findViewById(R.id.bWhip)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setAlpha(0.5F);
                        mp1.start();
                        mpLastSound = mp1;
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setAlpha(1F);
                        break;
                }
                return true;
            }
        });
        ((Button)findViewById(R.id.bDrama)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2.start();
                mpLastSound = mp2;
            }
        });

        ((Button)findViewById(R.id.bLaugh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp3.start();
                mpLastSound = mp3;
            }
        });

        ((Button)findViewById(R.id.bBadJoke)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp4.start();
                mpLastSound = mp4;
            }
        });

        ((Button)findViewById(R.id.bTada)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp5.start();
                mpLastSound = mp5;
            }
        });

        ((ImageButton)findViewById(R.id.bLastSound)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mpLastSound.start();
            }
        });

        whipDetector = new WhipDetector(this, new WhipDetector.Callback() {
            @Override
            public void whipNao() {
                /**
                 *
                 * whipSoundPlayer.gogogo(viewPager.getCurrentItem());
                 */
                Log.d("###", "!!NAO!!");
            }
        });




    }

    @Override
    protected void onStart() {
        Log.v("###", "MainActivity.onStart()");
        super.onStart();
        whipDetector.on();
    }

    @Override
    protected void onStop() {
        Log.v("###", "MainActivity.onStop()");
        super.onStop();
        whipDetector.off();
    }


    static class WhipDetector implements SensorEventListener {

        //private final Context context;
        private final Callback callback;
        private final SensorManager sensorManager;
        private int[] oldvalues;

        public WhipDetector (Context context, final Callback callback) {
            //this.context = context;
            this.callback = callback;
            this.sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
            this.oldvalues = new int[3];
        }

        public void on() {
            //sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_GAME);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void off(){
            sensorManager.unregisterListener(this);
        }

        public static boolean between(float x, float min, float max) {
            return x >= min && x <= max;
        }



        @Override
        // X : monter/descendre ; Y : Z : lateral ;

        public void onSensorChanged(SensorEvent event) {

            // round values
            int x,y,z;
            x = (int) event.values[0];
            y = (int) event.values[1];
            z = (int) event.values[2];
            if (oldvalues[0] != x || oldvalues[1] != y || oldvalues[2] != z) {

                // One axis has changed
                Log.d("###", String.format("Move X=%+3d Y=%+3d Z=%+3d", x,y,z));
                oldvalues[0] = x;
                oldvalues[1] = y;
                oldvalues[2] = z;
            }
            //Log.d("###", String.format("sensor value X=%+03.3f Y=%+03.3f Z=%+03.3f", event.values[0], event.values[1], event.values[2]));
            //callback.whipNao();

            if (event.values[2] > 4.5f) {
                if ( this.between(event.values[0], -1f, 1f) &&
                        this.between(event.values[1], -1f, 1f)) {
                    callback.whipNao();
                }
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        interface Callback {
            void whipNao();
        }
    }
}