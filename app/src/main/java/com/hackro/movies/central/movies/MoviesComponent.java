package com.hackro.movies.central.movies;

import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { MoviesModule.class }) public interface MoviesComponent {
  void inject(MoviesActivity MoviesActivity);
}
