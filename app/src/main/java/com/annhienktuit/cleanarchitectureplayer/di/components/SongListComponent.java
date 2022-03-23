package com.annhienktuit.cleanarchitectureplayer.di.components;

import com.annhienktuit.cleanarchitectureplayer.di.modules.SongListModule;
import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.ui.activities.SongListActivity;

import dagger.Component;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {SongListModule.class})
public interface SongListComponent {

    void inject(SongListActivity songListActivity);

}
