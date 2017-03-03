package com.hackro.movies.central;

import android.app.Application;

import com.hackro.movies.central.di.component.AppComponent;
import com.hackro.movies.central.di.component.DaggerAppComponent;
import com.hackro.movies.central.di.module.AppModule;
import com.hackro.movies.central.di.module.MoviesModule;

/**
 * Created by hackro on 28/02/17.
 */
public class MovieApplication  extends Application{

    private AppComponent  appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .moviesModule(new MoviesModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
