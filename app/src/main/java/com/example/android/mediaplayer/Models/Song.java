package com.example.android.mediaplayer.Models;

/**
 * Created by Adrian on 8/10/2017.
 */

public class Song {
    private String title;
    private String band;
    private int mediaResourceId;

    public Song(String t, String b, int res) {
        title = t;
        band = b;
        mediaResourceId = res;
    }

    public int getMediaResourceId() {
        return mediaResourceId;
    }

    public String getBand() {
        return band;
    }

    public String getTitle() {
        return title;
    }

}
