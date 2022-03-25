package com.annhienktuit.data.networks;

import com.annhienktuit.data.models.SongModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetSongService {

    @GET("api/v1/Song/{songID}")
    Observable<SongModel> getSong(@Path("songID") int songID);

    @GET("api/v1/Song")
    Observable<List<SongModel>> getAllSong();

}
