package com.annhienktuit.cleanarchitectureplayer;

import android.app.Application;

import com.annhienktuit.data.networks.RetrofitSongDataSource;
import com.annhienktuit.domain.interfaces.SongDataSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultServiceLocator implements ServiceLocator {

    private static DefaultServiceLocator instance;
    private RetrofitSongDataSource retrofitSongDataSource;
    private ExecutorService ioExecutorService;
    private ExecutorService mainExecutorService;

    public static DefaultServiceLocator getInstance(Application application) {
        if (instance == null) {
            instance = new DefaultServiceLocator(application);
        }
        return instance;
    }

    public DefaultServiceLocator(Application application) {
    }

    @Override
    public ExecutorService getIoExecutorService() {
        if (ioExecutorService == null) {
            ioExecutorService = Executors.newCachedThreadPool();
        }
        return ioExecutorService;
    }

    @Override
    public ExecutorService getMainExecutorService() {
        if (mainExecutorService == null) {
            mainExecutorService = new MainThreadExecutorService();
        }
        return mainExecutorService;
    }

    @Override
    public SongDataSource getSongDataSource() {
        if(retrofitSongDataSource == null){
            retrofitSongDataSource = new RetrofitSongDataSource();
        }
        return retrofitSongDataSource;
    }
}
