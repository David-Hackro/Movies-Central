package com.hackro.movies.central.view.model;

import java.util.List;

public class MoviesPresentation {

  private String genre;
  private List<Result> moviesList;


  public MoviesPresentation(String genre, List<Result> moviesList) {
    this.genre = genre;
    this.moviesList = moviesList;
  }

  public MoviesPresentation() {
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public List<Result> getMoviesList() {
    return moviesList;
  }

  public void setMoviesList(List<Result> moviesList) {
    this.moviesList = moviesList;
  }


}
