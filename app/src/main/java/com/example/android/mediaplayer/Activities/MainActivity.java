package com.example.android.mediaplayer.Activities;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSongsToPlaylist();

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position);
                Intent intent = new Intent(MainActivity.this, SongActivity.class);
                intent.putExtra(getStringFromResId(R.string.bandName), song.getBand());
                intent.putExtra(getStringFromResId(R.string.songName), song.getTitle());
                startActivity(intent);
            }
        });
    }

    public void addSongsToPlaylist() {
        songs.add(new Song(getStringFromResId(R.string.sad), getStringFromResId(R.string.metallica)));
        songs.add(new Song(getStringFromResId(R.string.one), getStringFromResId(R.string.metallica)));
        songs.add(new Song(getStringFromResId(R.string.unforgiven), getStringFromResId(R.string.metallica)));
        songs.add(new Song(getStringFromResId(R.string.lithium), getStringFromResId(R.string.nirvana)));
        songs.add(new Song(getStringFromResId(R.string.come), getStringFromResId(R.string.nirvana)));
        songs.add(new Song(getStringFromResId(R.string.the_man), getStringFromResId(R.string.nirvana)));
        songs.add(new Song(getStringFromResId(R.string.californication), getStringFromResId(R.string.rhcp)));
        songs.add(new Song(getStringFromResId(R.string.otherside), getStringFromResId(R.string.rhcp)));
        songs.add(new Song(getStringFromResId(R.string.under_the_bridge), getStringFromResId(R.string.rhcp)));
    }

    private String getStringFromResId(int resId) {
        return getResources().getString(resId);
    }
}
