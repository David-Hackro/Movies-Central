package com.hackro.movies.central.di.component;


import com.hackro.movies.central.data.remote.AppRemoteData;
import com.hackro.movies.central.di.module.AppModule;
import com.hackro.movies.central.di.module.MoviesModule;
import com.hackro.movies.central.view.activity.home.MoviesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hackro on 27/02/17.
 */

@Singleton
@Component(modules = {MoviesModule.class, AppModule.class})
public interface AppComponent {
    void inject(MoviesActivity moviesActivity);
    void inject(AppRemoteData appRemoteData);


}
