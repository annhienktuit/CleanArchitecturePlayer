package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import android.util.Log;

import com.annhienktuit.cleanarchitectureplayer.ui.views.SongListView;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SongListPresenterImpl implements SongListPresenter {

    private SongListView songListView;

    @Inject
    GetSongUseCase getSongUseCase;

    @Inject
    public SongListPresenterImpl() { }

    @Override
    public void loadSong(){

        getSongUseCase.executeAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Song>>() {
                    @Override
                    public void onNext(@NonNull List<Song> songs) {
                        songListView.showSongList(songs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        songListView.showErrorToast("Error while getting data from API");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Nhiennha ", "Completed");
                    }
                });
    }

    @Override
    public void onSongItemClick(Song song){
            songListView.openSong(song);
    }

    @Override
    public void attachView(SongListView view) {
        this.songListView = view;
    }

}
