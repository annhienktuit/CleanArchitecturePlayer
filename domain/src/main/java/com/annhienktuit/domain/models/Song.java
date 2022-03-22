package com.annhienktuit.domain.models;

//Model
public class Song {
    String id;

    String title;

    String songURL;

    String artist;

    public Song(String id, String title, String songURL, String artist) {
        this.id = id;
        this.title = title;
        this.songURL = songURL;
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

