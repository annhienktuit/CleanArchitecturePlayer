package com.annhienktuit.cleanarchitectureplayer.di.components;

import com.annhienktuit.cleanarchitectureplayer.di.modules.DataSourceModule;
import com.annhienktuit.cleanarchitectureplayer.di.modules.ExecutorModule;
import com.annhienktuit.cleanarchitectureplayer.di.modules.UseCaseModule;
import com.annhienktuit.cleanarchitectureplayer.ui.player.PlayerActivity;
import com.annhienktuit.cleanarchitectureplayer.ui.songlist.SongListListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ExecutorModule.class, DataSourceModule.class, UseCaseModule.class})
public interface ApplicationComponent {

    void inject(SongListListActivity songListListActivity);

    void inject(PlayerActivity playerActivity);

}
