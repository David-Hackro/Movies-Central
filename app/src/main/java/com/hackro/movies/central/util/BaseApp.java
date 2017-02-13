package com.hackro.movies.central.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.hackro.movies.central.movies.DaggerMoviesComponent;
import com.hackro.movies.central.movies.MoviesComponent;
import com.hackro.movies.central.movies.MoviesModule;
import java.io.File;

public class BaseApp extends AppCompatActivity {
  private MoviesComponent mainComponent;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    File cacheFile = new File(getCacheDir(), "responses");
    mainComponent =
        DaggerMoviesComponent.builder().moviesModule(new MoviesModule(cacheFile)).build();
  }

  public MoviesComponent getMainComponent() {
    return mainComponent;
  }
}