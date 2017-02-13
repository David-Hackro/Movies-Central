package com.hackro.movies.central.movies.models;

import java.util.List;

public class GenerosResponse {

  private List<Genre> genres = null;

  /**
   * No args constructor for use in serialization
   */
  public GenerosResponse() {
  }

  /**
   * @param genres
   */
  public GenerosResponse(List<Genre> genres) {
    super();
    this.genres = genres;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }
}
