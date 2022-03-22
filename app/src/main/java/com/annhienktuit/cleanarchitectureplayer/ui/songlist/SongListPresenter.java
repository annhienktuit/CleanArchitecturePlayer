package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import com.annhienktuit.domain.models.Song;

public interface SongListPresenter {

    void loadSong();

    void onSongItemClick(Song song);
}
