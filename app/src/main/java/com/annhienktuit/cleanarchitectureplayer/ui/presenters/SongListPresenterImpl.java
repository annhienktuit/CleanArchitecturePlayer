package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import android.util.Log;

import com.annhienktuit.cleanarchitectureplayer.ui.views.SongListView;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class SongListPresenterImpl implements SongListPresenter {
    private SongListView songListView;
    private GetSongUseCase getSongUseCase;
    private ExecutorService ioExecutorService;
    private ExecutorService mainExecutorService;

    public SongListPresenterImpl(SongListView songListView, GetSongUseCase getSongUseCase, ExecutorService ioExecutorService, ExecutorService mainExecutorService) {
        this.songListView = songListView;
        this.getSongUseCase = getSongUseCase;
        this.ioExecutorService = ioExecutorService;
        this.mainExecutorService = mainExecutorService;
    }

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

}
