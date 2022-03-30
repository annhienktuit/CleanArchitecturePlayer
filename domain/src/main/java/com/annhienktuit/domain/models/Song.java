package com.annhienktuit.domain.models;

import java.util.Objects;

public class Song {
    String id;

    String title;

    String songURL;

    String artist;

    public Song(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(getId(), song.getId()) && Objects.equals(getTitle(), song.getTitle()) && Objects.equals(getSongURL(), song.getSongURL()) && Objects.equals(getArtist(), song.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getSongURL(), getArtist());
    }
}

