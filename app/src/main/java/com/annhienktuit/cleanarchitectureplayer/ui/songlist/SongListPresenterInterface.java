package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import com.annhienktuit.domain.models.Song;

public interface SongListPresenterInterface {

    void loadSong();

    void onSongItemClick(Song song);
}
