package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nhien Nguyen on 3/23/2022
 */
@Module
public class ExecutorModule {

    @Provides
    @Named("MainThread")
    public AbstractExecutorService provideMainThreadExecutorService(){
        return new MainThreadExecutorService();
    }

    @Provides
    @Named("IOThread")
    public ExecutorService provideIoExecutorService(){
        return Executors.newCachedThreadPool();
    }

}
