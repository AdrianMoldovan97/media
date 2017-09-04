package com.example.android.mediaplayer.Models;

public class Song {
    private String title;
    private String band;

    public Song(String title, String band) {
        this.title = title;
        this.band = band;
    }

    public String getBand() {
        return band;
    }

    public String getTitle() {
        return title;
    }

}
