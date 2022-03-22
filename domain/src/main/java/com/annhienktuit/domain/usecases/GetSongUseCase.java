package com.annhienktuit.domain.usecases;

import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import javax.inject.Inject;

//Usecase
public class GetSongUseCase {
    @Inject
    SongDataSource songDataSource;

    @Inject
    public GetSongUseCase(){

    }

    public Song execute(int id) throws Exception {
        return songDataSource.getSong(id);
    }

    public List<Song> executeAll() throws Exception {
        return songDataSource.getAllSong();
    }

}
