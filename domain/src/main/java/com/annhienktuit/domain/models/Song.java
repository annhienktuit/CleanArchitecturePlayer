package com.annhienktuit.domain.models;

import com.google.gson.annotations.SerializedName;

public class Song {
    @SerializedName("id")
    String id;

    @SerializedName("song_name")
    String title;

    @SerializedName("url")
    String songURL;

    @SerializedName("artist")
    String artist;

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

