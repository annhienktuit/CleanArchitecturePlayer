package com.annhienktuit.data.networks;

import com.annhienktuit.data.mappers.Mapper;
import com.annhienktuit.data.mappers.SongMapper;
import com.annhienktuit.data.models.SongModel;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSongDataSource implements SongDataSource {

    private static final String BASE_URL = "https://61a03c9da6470200176132f7.mockapi.io/";

    private final Mapper<Song, SongModel> mapper = new SongMapper();

    public Retrofit retrofit;

    GetSongService getSongService;

    @Inject
    public RetrofitSongDataSource() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        getSongService = retrofit.create(GetSongService.class);
    }

    @Override
    public Observable<Song> getSong(int id) {
        return getSongService.getSong(id).map(mapper::fromModel);
    }

    @Override
    public Observable<List<Song>> getAllSong() {
        return getSongService.getAllSong().map(mapper::fromModel);
    }
}
