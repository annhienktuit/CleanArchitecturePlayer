package com.annhienktuit.cleanarchitectureplayer.di.modules;


import android.util.Log;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.di.ApplicationScope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorModule {

    private final MainThreadExecutorService mainThreadExecutorService;

    private final ExecutorService ioExecutorService;

    public ExecutorModule(){
        mainThreadExecutorService = new MainThreadExecutorService();
        ioExecutorService = Executors.newCachedThreadPool();
        Log.i("Nhiennha ", String.valueOf(mainThreadExecutorService.hashCode()));
    }

    @ApplicationScope
    @Provides
    public MainThreadExecutorService provideMainThreadExecutorService(){
        return mainThreadExecutorService;
    }

    @ApplicationScope
    @Provides
    public ExecutorService provideIoExecutorService(){
        return ioExecutorService;
    }

}
