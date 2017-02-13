package com.hackro.movies.central.movies;

import com.hackro.movies.central.movies.models.CollectionsMovies;
import java.util.List;

interface MoviesView {

  void showLoadingView();

  void hideLoadingView();

  void showFailureMessage(Throwable error);

  void showMoviesView(List<CollectionsMovies> collectionList);
}
