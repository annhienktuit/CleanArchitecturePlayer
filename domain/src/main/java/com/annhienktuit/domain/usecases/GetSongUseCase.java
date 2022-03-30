package com.annhienktuit.domain.usecases;

import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetSongUseCase {
    @Inject
    SongDataSource songDataSource;

    @Inject
    public GetSongUseCase() {

    }

    public Observable<Song> execute(int id) {
        return songDataSource.getSong(id);
    }

    public Observable<List<Song>> executeAll() {
        return songDataSource.getAllSong();
    }
}
