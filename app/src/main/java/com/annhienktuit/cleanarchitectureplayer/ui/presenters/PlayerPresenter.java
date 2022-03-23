package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import com.annhienktuit.cleanarchitectureplayer.ui.views.PlayerView;
import com.google.android.exoplayer2.ExoPlayer;

public interface PlayerPresenter {

    void initializeMedia(int id);

    void startPlay(String url);

    void releasePlayer();

    void attachView(PlayerView view);

    void attachPlayer(ExoPlayer player);
}
