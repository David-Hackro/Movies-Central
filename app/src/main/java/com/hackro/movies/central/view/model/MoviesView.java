package com.hackro.movies.central.view.model;

import com.hackro.movies.central.data.local.model.Genre;
import com.hackro.movies.central.data.local.model.ResponseMovies;

public class MoviesView {

  private Genre genre;
  private ResponseMovies movies;



  public MoviesView(Genre genres, ResponseMovies movies) {
    this.genre = genres;
    this.movies = movies;
  }


  public MoviesView() {
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
