package com.annhienktuit.cleanarchitectureplayer;

import android.app.Application;

import com.annhienktuit.cleanarchitectureplayer.di.components.ApplicationComponent;
import com.annhienktuit.cleanarchitectureplayer.di.components.DaggerApplicationComponent;
import com.annhienktuit.cleanarchitectureplayer.di.modules.AppModule;

/**
 * Created by Nhien Nguyen on 3/22/2022
 */
public class App extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
        applicationComponent.inject(this);
    }

}