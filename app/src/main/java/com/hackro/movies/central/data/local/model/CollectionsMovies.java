package com.hackro.movies.central.data.local.model;

public class CollectionsMovies {

  private final Genre genre;
  private final ResponseMovies movies;

  public CollectionsMovies(Genre genres, ResponseMovies movies) {
    this.genre = genres;
    this.movies = movies;
  }

  public Genre getGenre() {
    return genre;
  }

  public ResponseMovies getMovies() {
    return movies;
  }
}
