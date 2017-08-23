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
        mediaResourceId = extras.getInt(getStringFromResId(R.string.media));

        TextView bandNameTextView = (TextView) findViewById(R.id.bandNameTextView);
        bandNameTextView.setText(band);
        bandNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, BandDetailActivity.class);
                intent.putExtra(getStringFromResId(R.string.bandNameActivity), band);
                startActivity(intent);
            }
        });

        TextView songNameTextView = (TextView) findViewById(R.id.songNameTextView);
        songNameTextView.setText(songTitle);
        songNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongActivity.this, SongDetailActivity.class);
                intent.putExtra(getStringFromResId(R.string.songNameActivity), songTitle);
                startActivity(intent);
            }
        });
    }

    private String getStringFromResId(int resId) {
        return getResources().getString(resId);
    }
}
