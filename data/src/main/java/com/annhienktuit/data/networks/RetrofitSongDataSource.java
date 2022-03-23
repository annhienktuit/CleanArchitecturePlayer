package com.annhienktuit.data.networks;

import com.annhienktuit.data.mappers.SongMapper;
import com.annhienktuit.data.models.SongModel;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSongDataSource implements SongDataSource {

    private static final String BASE_URL = "https://61a03c9da6470200176132f7.mockapi.io/";

    private final SongMapper<Song, SongModel> mapper = new SongMapper<>();

    @Inject
    public RetrofitSongDataSource(){}

    private final GetSongService getSongService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GetSongService.class);

    @Override
    public Song getSong(int id) throws Exception {
        Response<SongModel> response = getSongService.getSong(id).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            return mapper.fromModel(response.body());
        } else {
            throw new Exception(response.message());
        }
    }

    @Override
    public List<Song> getAllSong() throws Exception {
        List<Song> songList = new ArrayList<>();
        Response<List<SongModel>> responses = getSongService.getAllSong().execute();
        if (responses.isSuccessful()) {
            assert responses.body() != null;
            for(SongModel response: responses.body()){
                songList.add(mapper.fromModel(response));
            }
            return songList;
        } else {
            throw new Exception(responses.message());
        }
    }

}
