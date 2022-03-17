package com.annhienktuit.domain.interfaces;

import com.annhienktuit.domain.models.Song;

import java.util.List;

//DataSource
public interface SongDataSource {

    Song getSong(int id) throws Exception;

    List<Song> getAllSong() throws Exception;
}
