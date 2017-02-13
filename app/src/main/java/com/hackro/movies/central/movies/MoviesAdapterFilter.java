package com.hackro.movies.central.movies;

interface MoviesAdapterFilter {

  void orderAlphabetically();

  void orderDate();

  void orderStars();

  void findName(String name);

  void findStars(Double vote_average);

  void resetFilters();
}
