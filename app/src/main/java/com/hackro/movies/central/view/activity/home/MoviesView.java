package com.hackro.movies.central.view.activity.home;


import java.util.List;

interface MoviesView {

  void showLoadingView();

  void hideLoadingView();

  void showFailureMessage(Throwable error);

  void showMoviesView(List<com.hackro.movies.central.view.model.MoviesView> MoviesView);
}
