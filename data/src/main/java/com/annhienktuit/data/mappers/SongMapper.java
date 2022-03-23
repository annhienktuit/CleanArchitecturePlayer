package com.annhienktuit.data.mappers;

import com.annhienktuit.data.models.SongModel;
import com.annhienktuit.domain.models.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
public class SongMapper<E, M> implements Mapper<Song, SongModel> {

    public SongMapper(){};

    @Override
    public Song fromModel(SongModel model) {
        return new Song(
                model.getId(),
                model.getTitle(),
                model.getSongURL(),
                model.getArtist()
        );
    }

    @Override
    public SongModel fromEntity(Song entity) {
        return new SongModel(
                entity.getId(),
                entity.getTitle(),
                entity.getSongURL(),
                entity.getArtist()
        );
    }
}
