package com.annhienktuit.cleanarchitectureplayer.ui.player;

import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

import java.util.concurrent.ExecutorService;

public class ExoPlayerPresenter {

    private ExoPlayer exoPlayer;

    private ExoPlayerView exoPlayerView;

    private GetSongUseCase getSongUseCase;

    private ExecutorService ioExecutorService;

    private ExecutorService mainExecutorService;

    public ExoPlayerPresenter(ExoPlayer exoPlayer, ExoPlayerView exoPlayerView, GetSongUseCase getSongUseCase, ExecutorService ioExecutorService, ExecutorService mainExecutorService) {
        this.exoPlayer = exoPlayer;
        this.exoPlayerView = exoPlayerView;
        this.getSongUseCase = getSongUseCase;
        this.ioExecutorService = ioExecutorService;
        this.mainExecutorService = mainExecutorService;
    }

    public void startPlay(String url) {
        MediaItem mediaItem = MediaItem.fromUri(url);
        exoPlayer.addMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
    }

    public void initializeMedia(int id) {
        mainExecutorService.execute(() -> {
            exoPlayerView.showPlayer();

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
        });
    }
}
