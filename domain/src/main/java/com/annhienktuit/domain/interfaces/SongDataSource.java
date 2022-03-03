package com.annhienktuit.domain.interfaces;

import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.models.SongList;

import java.util.List;

public interface SongDataSource {

    Song getSong(int id) throws Exception;

    List<Song> getAllSong() throws Exception;
}
