package org.jfo.app.makesomenoise;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Button bt;

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

        ((Button)findViewById(R.id.bWhip)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp1.start();
            }
        });

        ((Button)findViewById(R.id.bDrama)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2.start();
            }
        });

        ((Button)findViewById(R.id.bLaugh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp3.start();
            }
        });

        ((Button)findViewById(R.id.bBadJoke)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp4.start();
            }
        });

        ((Button)findViewById(R.id.bTada)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp5.start();
            }
        });




    }
}