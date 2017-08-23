package com.example.android.mediaplayer.Activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.mediaplayer.Adapters.SongAdapter;
import com.example.android.mediaplayer.Models.Song;
import com.example.android.mediaplayer.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Song> songs = new ArrayList<>();
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        addSongsToPlaylist();

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position);

                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, song.getMediaResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                    Intent intent = new Intent(MainActivity.this, SongActivity.class);
                    intent.putExtra(getStringFromResId(R.string.bandName), song.getBand());
                    intent.putExtra(getStringFromResId(R.string.songName), song.getTitle());
                    intent.putExtra(getStringFromResId(R.string.media), song.getMediaResourceId());
                    startActivity(intent);
                }
            }
        });
    }

    public void addSongsToPlaylist() {
        songs.add(new Song(getStringFromResId(R.string.sad), getStringFromResId(R.string.metallica), R.raw.sad));
        songs.add(new Song(getStringFromResId(R.string.one), getStringFromResId(R.string.metallica), R.raw.one));
        songs.add(new Song(getStringFromResId(R.string.unforgiven), getStringFromResId(R.string.metallica), R.raw.unforgiven));
        songs.add(new Song(getStringFromResId(R.string.lithium), getStringFromResId(R.string.nirvana), R.raw.lithium));
        songs.add(new Song(getStringFromResId(R.string.come), getStringFromResId(R.string.nirvana), R.raw.come));
        songs.add(new Song(getStringFromResId(R.string.the_man), getStringFromResId(R.string.nirvana), R.raw.theman));
        songs.add(new Song(getStringFromResId(R.string.californication), getStringFromResId(R.string.rhcp), R.raw.californication));
        songs.add(new Song(getStringFromResId(R.string.otherside), getStringFromResId(R.string.rhcp), R.raw.otherside));
        songs.add(new Song(getStringFromResId(R.string.under_the_bridge), getStringFromResId(R.string.rhcp), R.raw.under));
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    private String getStringFromResId(int resId) {
        return getResources().getString(resId);
    }
}
