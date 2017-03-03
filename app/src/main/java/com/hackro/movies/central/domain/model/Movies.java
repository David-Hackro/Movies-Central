package com.hackro.movies.central.domain.model;

import com.hackro.movies.central.data.local.model.Genre;
import com.hackro.movies.central.data.local.model.ResponseMovies;

public class Movies {

  private Genre genre;
  private ResponseMovies movies;

  public Movies() {
  }

  public Movies(Genre genres, ResponseMovies movies) {
    this.genre = genres;
    this.movies = movies;
  }

  public Genre getGenre() {
    return genre;
  }

  public ResponseMovies getMovies() {
    return movies;
  }


  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public void setMovies(ResponseMovies movies) {
    this.movies = movies;
  }
}
