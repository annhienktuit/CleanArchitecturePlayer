package com.annhienktuit.data.networks;

import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.models.SongList;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetSongService {

    @GET("api/v1/Song/{songID}")
    Call<Song> getSong(@Path("songID") int songID);

    @GET("api/v1/Song")
    Call<List<Song>> getAllSong();

}
