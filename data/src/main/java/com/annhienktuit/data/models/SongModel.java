package com.annhienktuit.data.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
public class SongModel {

    @SerializedName("id")
    String id;

    @SerializedName("song_name")
    String title;

    @SerializedName("url")
    String songURL;

    @SerializedName("artist")
    String artist;

    public SongModel(){}

    public SongModel(String id, String title, String songURL, String artist) {
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
