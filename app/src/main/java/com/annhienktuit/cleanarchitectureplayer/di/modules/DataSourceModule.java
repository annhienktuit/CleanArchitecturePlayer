package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.data.networks.RetrofitSongDataSource;
import com.annhienktuit.domain.interfaces.SongDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    private final SongDataSource onlineDataSource = new RetrofitSongDataSource();

    @Provides
    public SongDataSource provideOnlineDataSource(){
        return onlineDataSource;
    }

}
