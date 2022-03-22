package com.annhienktuit.cleanarchitectureplayer.di.modules;

import android.app.Application;
import android.content.Context;

import com.annhienktuit.cleanarchitectureplayer.di.scopes.ApplicationScope;
import com.annhienktuit.data.networks.RetrofitSongDataSource;
import com.annhienktuit.domain.interfaces.SongDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application app){
        this.application = app;
    }

    @ApplicationScope
    @Provides
    Context provideContext(){
        return application;
    }

    @Provides
    SongDataSource provideSongDataSource() {
        return new RetrofitSongDataSource();
    }

}
