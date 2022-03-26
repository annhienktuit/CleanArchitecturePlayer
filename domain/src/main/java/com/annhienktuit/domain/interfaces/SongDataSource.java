package com.annhienktuit.domain.interfaces;

import com.annhienktuit.domain.models.Song;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public interface SongDataSource {

    Observable<Song> getSong(int id);

    Observable<List<Song>> getAllSong();
}
