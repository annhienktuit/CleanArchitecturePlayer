package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.data.networks.RetrofitSongDataSource;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    @Named("RetrofitDataSource")
    SongDataSource provideSongDataSource(){
        return new RetrofitSongDataSource();
    }

    @Singleton
    @Provides
    GetSongUseCase provideGetSongUseCase(@Named("RetrofitDataSource") SongDataSource retrofitSongDataSource){
        return new GetSongUseCase(retrofitSongDataSource);
    }

}
