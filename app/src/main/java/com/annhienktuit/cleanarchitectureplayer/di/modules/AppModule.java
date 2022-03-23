package com.annhienktuit.cleanarchitectureplayer.di.modules;

import android.app.Application;
import android.content.Context;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.di.scopes.ApplicationScope;
import com.annhienktuit.data.networks.RetrofitSongDataSource;
import com.annhienktuit.domain.interfaces.SongDataSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ExecutorModule.class})
public class AppModule {

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
