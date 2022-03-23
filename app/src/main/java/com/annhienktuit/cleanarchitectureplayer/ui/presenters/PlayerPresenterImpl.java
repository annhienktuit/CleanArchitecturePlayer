package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.ui.views.PlayerView;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Named;

public class PlayerPresenterImpl implements PlayerPresenter {

    private ExoPlayer exoPlayer;

    private PlayerView playerView;

    @Inject
    GetSongUseCase getSongUseCase;

    @Inject
    @Named("IOThread")
    ExecutorService ioExecutorService;

    @Inject
    @Named("MainThread")
    AbstractExecutorService mainExecutorService = new MainThreadExecutorService();

    @Inject
    public PlayerPresenterImpl() {
    }

    @Override
    public void startPlay(String url) {
        MediaItem mediaItem = MediaItem.fromUri(url);
        exoPlayer.addMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
    }

    @Override
    public void releasePlayer() {
        exoPlayer.stop();
        exoPlayer.release();
    }

    @Override
    public void attachView(PlayerView view) {
        this.playerView = view;
    }

    @Override
    public void attachPlayer(ExoPlayer player) {
        this.exoPlayer = player;
    }

    @Override
    public void initializeMedia(int id) {
            playerView.showPlayer();
            ioExecutorService.execute(() -> {
                try {
                    Song song = getSongUseCase.execute(id);
                    if (song != null) {
                        mainExecutorService.execute(() -> {
                            startPlay(song.getSongURL());
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }
}
