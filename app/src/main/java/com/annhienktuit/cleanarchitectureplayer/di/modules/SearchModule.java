package com.annhienktuit.cleanarchitectureplayer.di.modules;

import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.SearchPresenter;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.SearchPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nhien Nguyen on 3/30/2022
 */
@Module
public class SearchModule {

    @ActivityScope
    @Provides
    SearchPresenter provideSearchPresenter(SearchPresenterImpl presenter){
        return presenter;
    }

}
