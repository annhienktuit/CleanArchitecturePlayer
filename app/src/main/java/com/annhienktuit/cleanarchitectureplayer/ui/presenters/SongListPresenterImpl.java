package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import android.util.Log;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.ui.views.SongListView;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Named;

public class SongListPresenterImpl implements SongListPresenter {
    private SongListView songListView;

    @Inject
    GetSongUseCase getSongUseCase;

    @Inject
    @Named("IOThread")
    ExecutorService ioExecutorService;

    @Inject
    @Named("MainThread")
    AbstractExecutorService mainExecutorService;

    @Inject
    public SongListPresenterImpl() { }

    @Override
    public void loadSong(){
        mainExecutorService.execute(() -> {
            ioExecutorService.execute(() -> {
                try{
                    List<Song> songList = getSongUseCase.executeAll();
                    if(songList != null){
                        mainExecutorService.execute(() -> {
                            songListView.showSongList(songList);
                        });
                    }
                    else {
                        songListView.showNoResultText();
                        Log.e("Nhiennha ", "List song return null");
                    }
                }
                catch (Exception e){
                    songListView.showErrorToast("Error while getting data from API");
                    e.printStackTrace();
                }
            });

        });
    }

    @Override
    public void onSongItemClick(Song song){
        mainExecutorService.execute(() -> {
            songListView.openSong(song);
        });
    }

    @Override
    public void attachView(SongListView view) {
        this.songListView = view;
    }

}
