package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.di.scopes.ApplicationScope;

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

//    @Provides
//    @ApplicationScope
//    @Named("MainThread")
//    public MainThreadExecutorService provideMainThreadExecutorService(){
//        return new MainThreadExecutorService();
//    }

    @Provides
    @ApplicationScope
    @Named("IOThread")
    public ExecutorService provideIoExecutorService(){
        return Executors.newCachedThreadPool();
    }

}
