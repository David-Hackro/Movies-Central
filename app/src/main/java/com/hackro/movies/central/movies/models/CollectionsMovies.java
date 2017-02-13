package com.hackro.movies.central.movies.models;

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
