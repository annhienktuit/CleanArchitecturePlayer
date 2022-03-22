package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import com.annhienktuit.cleanarchitectureplayer.ui.views.PlayerView;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

import java.util.concurrent.ExecutorService;

public class PlayerPresenterImpl implements PlayerPresenter {

    private ExoPlayer exoPlayer;

    private PlayerView playerView;

    private GetSongUseCase getSongUseCase;

    private ExecutorService ioExecutorService;

    private ExecutorService mainExecutorService;

    public PlayerPresenterImpl(ExoPlayer exoPlayer, PlayerView playerView, GetSongUseCase getSongUseCase, ExecutorService ioExecutorService, ExecutorService mainExecutorService) {
        this.exoPlayer = exoPlayer;
        this.playerView = playerView;
        this.getSongUseCase = getSongUseCase;
        this.ioExecutorService = ioExecutorService;
        this.mainExecutorService = mainExecutorService;
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