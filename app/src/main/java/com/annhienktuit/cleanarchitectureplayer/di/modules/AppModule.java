package com.annhienktuit.cleanarchitectureplayer.di.modules;

import android.app.Application;
import android.content.Context;

import com.annhienktuit.cleanarchitectureplayer.di.ApplicationScope;

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

}
