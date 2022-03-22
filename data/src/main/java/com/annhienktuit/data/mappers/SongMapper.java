package com.annhienktuit.data.mappers;

import com.annhienktuit.data.models.SongData;
import com.annhienktuit.domain.models.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
public class SongMapper {

    public SongMapper(){};

    public Song convertToSong(SongData songData){
        return new Song(
                songData.getId(),
                songData.getTitle(),
                songData.getSongURL(),
                songData.getArtist()
        );
    }

    public List<Song> convertToListSong(List<SongData> songDataList){
        List<Song> result = new ArrayList<>();
        for (SongData songData: songDataList) {
            result.add(convertToSong(songData));
        }
        return result;
    }

}
