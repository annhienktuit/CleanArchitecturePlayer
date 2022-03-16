package com.annhienktuit.cleanarchitectureplayer.di.components;

import com.annhienktuit.cleanarchitectureplayer.di.modules.DataSourceModule;
import com.annhienktuit.cleanarchitectureplayer.di.modules.ExecutorModule;
import com.annhienktuit.cleanarchitectureplayer.ui.player.PlayerActivity;
import com.annhienktuit.cleanarchitectureplayer.ui.songlist.SongListListActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Reusable;

@Singleton
@Component(modules = {ExecutorModule.class, DataSourceModule.class})
public interface ApplicationComponent {

    void inject(SongListListActivity songListListActivity);

    void inject(PlayerActivity playerActivity);

}
