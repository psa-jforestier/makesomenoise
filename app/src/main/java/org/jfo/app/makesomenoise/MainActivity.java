package org.jfo.app.makesomenoise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WhipDetector whipDetector;

    MediaPlayer mpLastSound;
    MediaPlayer[] mp;

    public int lastResId;
    public View lastView;
    private void playSound(int mpId) {

        if (!mp[mpId].isPlaying())
        {
            Log.d("###", "Starting sound number " + mpId);
            mp[mpId].start();
        }
        lastResId = mpId;
    }
    private void playSound(int mpId, View view) {
        playSound(mpId);
        lastView = view;
        view.setAlpha(0.5F);
        mp[mpId].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer paramMP) {
                view.setAlpha(1F);
                Log.d("###", "Ending   sound number " + mpId);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch(keyCode)   {
            case KeyEvent.KEYCODE_1 :b1Click((Button)findViewById(R.id.b1));
                break;
            case KeyEvent.KEYCODE_2 :b2Click((Button)findViewById(R.id.b2));
                break;
            case KeyEvent.KEYCODE_3 :b3Click((Button)findViewById(R.id.b3));
                break;
            case KeyEvent.KEYCODE_4 :b4Click((Button)findViewById(R.id.b4));
                break;
            case KeyEvent.KEYCODE_5 :b5Click((Button)findViewById(R.id.b5));
                break;
            case KeyEvent.KEYCODE_R :bClickLastsound((ImageButton)findViewById(R.id.bLastSound));
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
    /** Called when the user touches the button */
    public void b1Click(View view) {
        playSound(0, view);
    }

    public void b2Click(View view) {
        playSound(1, view);
    }
    public void b3Click(View view) {
        playSound(2, view);
    }
    public void b4Click(View view) {
        playSound(3, view);
    }
    public void b5Click(View view) {
        playSound(4, view);
    }
    public void bClickLastsound(View view) {
        if (!mp[lastResId].isPlaying())
            playSound(lastResId, view);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.mAbout:
                mHelpClick(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mHelpClick(View view)
    {
        String versionName = "???";
        try {
            PackageInfo packageinfo = MainActivity.this.getPackageManager()
                    .getPackageInfo(MainActivity.this.getPackageName(), 0);
            versionName = packageinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
        alertDialog.setTitle(getResources().getString(R.string.about));
        alertDialog.setMessage(versionName);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });

        alertDialog.show();  //<-- See This!
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ordered by buttons location on the UI
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.whip);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.dundundun);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.suspens);
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.laugh);
        final MediaPlayer mp5 = MediaPlayer.create(this, R.raw.badjoke);
        mp = new MediaPlayer[5];
        mp[0] = mp1;
        mp[1] = mp2;
        mp[2] = mp3;
        mp[3] = mp4;
        mp[4] = mp5;
        mpLastSound = mp1;
        lastResId = 0;//R.raw.whip;
        lastView = findViewById(R.id.b1);

        // TODO : better code to handle touch down.
        // Click is not sufficient, because I want the sound to be trigger on touch down, not on touch up.
        /**
        ((Button)findViewById(R.id.b1)).setOnTouchListener(new View.OnTouchListener() {
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
         **/
        whipDetector = new WhipDetector(this, new WhipDetector.Callback() {
            @Override
            public void whipNao() {
                Log.d("###", "Whip me dirty smartphone");
                //mpLastSound.start();
                playSound(lastResId, lastView);
            }
        });/**
        ((TextView)findViewById(R.id.lIntro)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });**/
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


    /**
     * Thank you https://codeberg.org/uniqx/AWhip/src/branch/main/app/src/main/java/org/getdisconnected/awhip/HomeActivity.java#L59
     */
    static class WhipDetector implements SensorEventListener {

        //private final Context context;
        private final Callback callback;
        private final SensorManager sensorManager;
        private int[] oldvalues;

        public WhipDetector (Context context, final Callback callback) {
            //this.context = context;
            this.callback = callback;

            this.sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
            List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            for (int i=0;i<deviceSensors.size();i++) {
                Log.d("###", "" + deviceSensors.get(i));
            }
            this.oldvalues = new int[3];
        }

        public void on() {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        }

        public void off(){
            sensorManager.unregisterListener(this);
        }

        public static boolean between(float x, float min, float max) {
            return x >= min && x <= max;
        }

        @Override
        public void onSensorChanged(SensorEvent event) {

            // round values
            //Log.d("###", String.format("sensor value X=%+03.3f Y=%+03.3f Z=%+03.3f", event.values[0], event.values[1], event.values[2]));

            int x,y,z;
            x = (int) event.values[0];
            y = (int) event.values[1];
            z = (int) event.values[2];
            if (oldvalues[0] != x || oldvalues[1] != y || oldvalues[2] != z) {

                // One axis has changed
                //Log.d("###", String.format("Move X=%+3d Y=%+3d Z=%+3d", x,y,z));
                oldvalues[0] = x;
                oldvalues[1] = y;
                oldvalues[2] = z;

                if (x > 30)
                {
                    Log.d("###", "WHIP detected !! " + String.format("Move X=%+3d Y=%+3d Z=%+3d", x,y,z));
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