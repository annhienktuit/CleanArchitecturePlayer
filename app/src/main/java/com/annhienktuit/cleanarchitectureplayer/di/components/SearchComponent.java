package com.annhienktuit.cleanarchitectureplayer.di.components;

import com.annhienktuit.cleanarchitectureplayer.di.modules.SearchModule;
import com.annhienktuit.cleanarchitectureplayer.di.scopes.ActivityScope;
import com.annhienktuit.cleanarchitectureplayer.ui.activities.SearchActivity;

import dagger.Component;

/**
 * Created by Nhien Nguyen on 3/30/2022
 */
@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {SearchModule.class})
public interface SearchComponent {

    void inject(SearchActivity searchActivity);

}
