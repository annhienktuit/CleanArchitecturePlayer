package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import com.annhienktuit.cleanarchitectureplayer.ui.views.SongListView;
import com.annhienktuit.domain.models.Song;

public interface SongListPresenter {

    void loadSong() throws Exception;

    void onSongItemClick(Song song);

    void attachView(SongListView view);
}
