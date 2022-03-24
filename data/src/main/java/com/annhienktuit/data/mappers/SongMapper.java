package com.annhienktuit.data.mappers;

import com.annhienktuit.data.models.SongModel;
import com.annhienktuit.domain.models.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
public class SongMapper implements Mapper<Song, SongModel> {

    public SongMapper(){}

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

    @Override
    public List<Song> fromModel(List<SongModel> modelList) {
        List<Song> songList = new ArrayList<>();
        for(SongModel songModel: modelList){
            songList.add(fromModel(songModel));
        }
        return songList;
    }

    @Override
    public List<SongModel> fromEntity(List<Song> entityList) {
        List<SongModel> songModels = new ArrayList<>();
        for(Song songEntity: entityList ){
            songModels.add(fromEntity(songEntity));
        }
        return songModels;
    }

}
