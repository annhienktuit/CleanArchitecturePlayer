package com.annhienktuit.cleanarchitectureplayer;

import com.annhienktuit.domain.interfaces.SongDataSource;

import java.util.concurrent.ExecutorService;

public interface ServiceLocator {
    ExecutorService getIoExecutorService();

    ExecutorService getMainExecutorService();

    SongDataSource getSongDataSource();
}
