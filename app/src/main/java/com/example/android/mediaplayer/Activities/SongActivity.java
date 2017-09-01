package com.example.android.mediaplayer.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.mediaplayer.R;

public class SongActivity extends AppCompatActivity {
    String band;
    String songTitle;
    int mediaResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Bundle extras = getIntent().getExtras();
        band = extras.getString(getStringFromResId(R.string.bandName));
        songTitle = extras.getString(getStringFromResId(R.string.songName));

        TextView bandNameTextView = (TextView) findViewById(R.id.bandNameTextView);
        bandNameTextView.setText(band);
        TextView songNameTextView = (TextView) findViewById(R.id.songNameTextView);
        songNameTextView.setText(songTitle);

        Button bandDetailButton = (Button) findViewById(R.id.bandDeatilsButton);
        bandDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, BandDetailActivity.class);
                startActivity(intent);
            }
        });

        Button songDetailButton = (Button) findViewById(R.id.songDeatilsButton);
        songDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, SongDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    private String getStringFromResId(int resId) {
        return getResources().getString(resId);
    }
}
