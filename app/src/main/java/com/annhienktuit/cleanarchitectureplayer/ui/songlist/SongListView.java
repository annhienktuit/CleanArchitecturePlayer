package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import com.annhienktuit.domain.models.Song;

import java.util.List;

public interface SongListView {
    void showSongList(List<Song> songList);

    void showNoResultText();

    void openSong(Song song);

    void showErrorToast(String error);
}
