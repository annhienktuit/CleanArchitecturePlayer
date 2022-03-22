package com.annhienktuit.cleanarchitectureplayer.di.components;

import android.app.Application;
import android.content.Context;

import com.annhienktuit.cleanarchitectureplayer.di.ApplicationScope;
import com.annhienktuit.cleanarchitectureplayer.di.modules.AppModule;
import com.annhienktuit.cleanarchitectureplayer.di.modules.DataSourceModule;
import com.annhienktuit.cleanarchitectureplayer.di.modules.ExecutorModule;
import com.annhienktuit.cleanarchitectureplayer.di.modules.UseCaseModule;
import com.annhienktuit.cleanarchitectureplayer.ui.activities.PlayerActivity;
import com.annhienktuit.cleanarchitectureplayer.ui.activities.SongListActivity;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class, ExecutorModule.class, DataSourceModule.class, UseCaseModule.class})
public interface ApplicationComponent {

    void inject(SongListActivity songListActivity);

    void inject(PlayerActivity playerActivity);

    void inject(Application app);

    Context getContext();

}
