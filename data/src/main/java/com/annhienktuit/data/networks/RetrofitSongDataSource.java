package com.annhienktuit.data.networks;

import android.util.Log;

import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.models.SongList;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSongDataSource implements SongDataSource {

    private static final String BASE_URL = "https://61a03c9da6470200176132f7.mockapi.io/";

    private final GetSongService getSongService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GetSongService.class);


    @Override
    public Song getSong(int id) throws Exception {
        Response<Song> response = getSongService.getSong(id).execute();
        Log.i("Nhiennha ", response.toString());
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }

    @Override
    public List<Song> getAllSong() throws Exception {
        Response<List<Song>> response = getSongService.getAllSong().execute();
        Log.i("Nhiennha ", String.valueOf(response.body().size()));
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }

}
