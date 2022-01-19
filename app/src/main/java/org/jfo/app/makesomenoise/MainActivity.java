package org.jfo.app.makesomenoise;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
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
        ((Button)findViewById(R.id.bWhip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp1.start();
                mpLastSound = mp1;
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




    }
}