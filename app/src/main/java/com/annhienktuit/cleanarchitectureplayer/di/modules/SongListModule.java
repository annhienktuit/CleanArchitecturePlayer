package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.SongListPresenter;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.SongListPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
@Module
public class SongListModule {

    @ActivityScope
    @Provides
    SongListPresenter provideSongListPresenter(SongListPresenterImpl presenter){
        return presenter;
    }

}
