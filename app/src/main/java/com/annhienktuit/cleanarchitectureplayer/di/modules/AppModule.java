package com.annhienktuit.cleanarchitectureplayer.di.modules;

import android.app.Application;
import android.content.Context;

import com.annhienktuit.data.networks.RetrofitSongDataSource;
import com.annhienktuit.domain.interfaces.SongDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final String BASE_URL = "https://61a03c9da6470200176132f7.mockapi.io/";

    private Application application;

    public AppModule(Application app){
        this.application = app;
    }

    @Provides
    Context provideContext(){
        return application;
    }

    @Provides
    SongDataSource provideSongDataSource() {
        return new RetrofitSongDataSource();
    }

}
