package com.annhienktuit.data.networks;

import android.util.Log;

import com.annhienktuit.data.mappers.Mapper;
import com.annhienktuit.data.mappers.SongMapper;
import com.annhienktuit.data.models.SongModel;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSongDataSource implements SongDataSource {

    private static final String BASE_URL = "https://61a03c9da6470200176132f7.mockapi.io/";

    Disposable disposable;

    Observer<SongModel> songModelObserver;

    private final Mapper<Song, SongModel> mapper = new SongMapper();
    GetSongService getSongService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GetSongService.class);

    @Inject
    public RetrofitSongDataSource() {

    }

//    @Override
//    public Song getSong(int id) throws Exception {
//        Response<SongModel> response = getSongService.getSong(id).execute();
//        if (response.isSuccessful()) {
//            assert response.body() != null;
//            return mapper.fromModel(response.body());
//        } else {
//            throw new Exception(response.message());
//        }
//    }
//
//    @Override
//    public List<Song> getAllSong() throws Exception {
//        Response<List<SongModel>> responses = getSongService.getAllSong().execute();
//        if (responses.isSuccessful()) {
//            assert responses.body() != null;
//            return mapper.fromModel(responses.body());
//        } else {
//            throw new Exception(responses.message());
//        }
//    }

    @Override
    public Observable<Song> getSong(int id) throws Exception {
        return getSongService.getSong(id).map(mapper::fromModel);
    }

    @Override
    public Observable<List<Song>> getAllSong() throws Exception {
        return getSongService.getAllSong().map(mapper::fromModel);
    }
}
