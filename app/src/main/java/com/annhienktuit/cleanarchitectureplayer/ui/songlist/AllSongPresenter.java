package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import android.util.Log;

import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.models.SongList;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class AllSongPresenter {
    private AllSongView allSongView;
    private GetSongUseCase getSongUseCase;
    private ExecutorService ioExecutorService;
    private ExecutorService mainExecutorService;

    public AllSongPresenter(AllSongView allSongView, GetSongUseCase getSongUseCase, ExecutorService ioExecutorService, ExecutorService mainExecutorService) {
        this.allSongView = allSongView;
        this.getSongUseCase = getSongUseCase;
        this.ioExecutorService = ioExecutorService;
        this.mainExecutorService = mainExecutorService;
    }

    public void loadSong(){
        mainExecutorService.execute(() -> {
            ioExecutorService.execute(() -> {
                try{
                    List<Song> songList = getSongUseCase.executeAll();
                    if(songList != null){
                        mainExecutorService.execute(() -> {
                            allSongView.showSongList(songList);
                        });
                    }
                    else {
                        allSongView.showNoResultText();
                        Log.e("Nhiennha ", "List song return null");
                    }
                }
                catch (Exception e){
                    allSongView.showErrorToast("Error while getting data from API");
                    e.printStackTrace();
                }
            });

        });
    }

    public void onSongItemClick(Song song){
        mainExecutorService.execute(() -> {
            allSongView.openSong(song);
        });
    }

}
