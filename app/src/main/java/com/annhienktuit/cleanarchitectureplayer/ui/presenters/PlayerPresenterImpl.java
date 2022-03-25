package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import android.util.Log;

import com.annhienktuit.cleanarchitectureplayer.ui.views.PlayerView;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlayerPresenterImpl implements PlayerPresenter {

    @Inject
    GetSongUseCase getSongUseCase;
    @Inject
    @Named("IOThread")
    ExecutorService ioExecutorService;
    @Inject
    @Named("MainThread")
    AbstractExecutorService mainExecutorService;
    private ExoPlayer exoPlayer;
    private PlayerView playerView;

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
    public void initializeMedia(int id) throws Exception {
        playerView.showPlayer();
        getSongUseCase.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Song>() {
                    @Override
                    public void onNext(@NonNull Song song) {
                        startPlay(song.getSongURL());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Nhiennha ", "Completed");
                    }
                });
    }
}
