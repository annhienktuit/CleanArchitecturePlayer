package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

public interface PlayerPresenter {

    void initializeMedia(int id);

    void startPlay(String url);

    void releasePlayer();
}
