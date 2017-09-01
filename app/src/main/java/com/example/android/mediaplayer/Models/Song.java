package com.example.android.mediaplayer.Models;

/**
 * Created by Adrian on 8/10/2017.
 */

public class Song {
    private String title;
    private String band;

    public Song(String t, String b) {
        title = t;
        band = b;
    }

    public String getBand() {
        return band;
    }

    public String getTitle() {
        return title;
    }

}
