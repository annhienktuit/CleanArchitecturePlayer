package com.annhienktuit.data.networks;

import com.annhienktuit.data.models.SongData;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetSongService {

    @GET("api/v1/Song/{songID}")
    Call<SongData> getSong(@Path("songID") int songID);

    @GET("api/v1/Song")
    Call<List<SongData>> getAllSong();

}
