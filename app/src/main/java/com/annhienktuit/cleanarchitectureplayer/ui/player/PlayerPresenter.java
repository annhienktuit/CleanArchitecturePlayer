package com.annhienktuit.cleanarchitectureplayer.ui.player;

public interface PlayerPresenter {

    void initializeMedia(int id);

    void startPlay(String url);

    void releasePlayer();
}
