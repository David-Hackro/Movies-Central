package com.hackro.movies.central.domain.model;

import java.util.List;

public class MoviesDomain {


  private String genre;
  private List<ResultDomain> moviesList;


  public MoviesDomain(String genre, List<ResultDomain> moviesList) {
    this.genre = genre;
    this.moviesList = moviesList;
  }

  public MoviesDomain() {
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public List<ResultDomain> getMoviesList() {
    return moviesList;
  }

  public void setMoviesList(List<ResultDomain> moviesList) {
    this.moviesList = moviesList;
  }


}
