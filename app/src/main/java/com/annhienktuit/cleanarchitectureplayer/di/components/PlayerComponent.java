package com.annhienktuit.cleanarchitectureplayer.di.components;

import com.annhienktuit.cleanarchitectureplayer.di.modules.PlayerModule;
import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.ui.activities.PlayerActivity;

import dagger.Component;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {PlayerModule.class})
public interface PlayerComponent {

    void inject(PlayerActivity playerActivity);

}
