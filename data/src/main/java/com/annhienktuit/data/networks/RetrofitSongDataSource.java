package com.annhienktuit.data.networks;

import android.util.Log;

import com.annhienktuit.data.mappers.SongMapper;
import com.annhienktuit.data.models.SongData;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//DataSourceImpl
public class RetrofitSongDataSource implements SongDataSource {

    private static final String BASE_URL = "https://61a03c9da6470200176132f7.mockapi.io/";

    SongMapper mapper = new SongMapper();

    private final GetSongService getSongService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(GetSongService.class);

    public RetrofitSongDataSource(){}

    @Override
    public Song getSong(int id) throws Exception {
        Response<SongData> response = getSongService.getSong(id).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            return mapper.convertToSong(response.body());
        } else {
            throw new Exception(response.message());
        }
    }

    @Override
    public List<Song> getAllSong() throws Exception {
        Response<List<SongData>> responses = getSongService.getAllSong().execute();
        if (responses.isSuccessful()) {
            assert responses.body() != null;
            return mapper.convertToListSong(responses.body());
        } else {
            throw new Exception(responses.message());
        }
    }

}
