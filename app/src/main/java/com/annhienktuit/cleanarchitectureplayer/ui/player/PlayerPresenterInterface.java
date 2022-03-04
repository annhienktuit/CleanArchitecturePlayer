package com.annhienktuit.cleanarchitectureplayer.ui.player;

public interface PlayerPresenterInterface {

    void initializeMedia(int id);

    void startPlay(String url);

    void releasePlayer();
}
