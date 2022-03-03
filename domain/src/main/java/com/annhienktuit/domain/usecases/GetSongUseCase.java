package com.annhienktuit.domain.usecases;

import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.List;

public class GetSongUseCase {
    private SongDataSource songDataSource;

    public GetSongUseCase(SongDataSource songDataSource){
        this.songDataSource = songDataSource;
    }

    public Song execute(int id) throws Exception {
        return songDataSource.getSong(id);
    }

    public List<Song> executeAll() throws Exception {
        return songDataSource.getAllSong();
    }

}
