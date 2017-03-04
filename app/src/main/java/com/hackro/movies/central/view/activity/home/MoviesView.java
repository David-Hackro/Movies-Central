package com.hackro.movies.central.view.activity.home;


import com.hackro.movies.central.view.model.MoviesPresentation;

import java.util.List;

interface MoviesView {

  void showLoadingView();

  void hideLoadingView();

  void showFailureMessage(Throwable error);

  void showMoviesView(List<MoviesPresentation> MoviesPresentation);
}
