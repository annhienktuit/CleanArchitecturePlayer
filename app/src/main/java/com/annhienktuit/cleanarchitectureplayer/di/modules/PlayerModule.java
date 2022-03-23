package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.PlayerPresenter;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.PlayerPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
@Module
public class PlayerModule {

    @ActivityScope
    @Provides
    PlayerPresenter providePlayerPresenter(PlayerPresenterImpl presenter){
        return presenter;
    }

}
