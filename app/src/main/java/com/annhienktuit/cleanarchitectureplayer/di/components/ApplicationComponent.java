package com.annhienktuit.cleanarchitectureplayer.di.components;

import android.app.Application;

import com.annhienktuit.cleanarchitectureplayer.di.modules.AppModule;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;

import javax.inject.Named;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(Application app);

    GetSongUseCase getSongUseCase();

}
