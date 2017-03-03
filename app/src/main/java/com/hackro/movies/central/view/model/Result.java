package com.hackro.movies.central.view.model;

import java.util.List;

public class Result {

  private boolean adult;
  private String backdropPath;
  private List<Integer> genreIds = null;
  private int id;
  private String originalLanguage;
  private String original_title;
  private String overview;
  private String release_date;
  private String poster_path;
  private double popularity;
  private String title;
  private boolean video;
  private Double vote_average;
  private int vote_count;

  /**
   * No args constructor for use in serialization
   */
  public Result() {
  }

  /**
   * @param id
   * @param genreIds
   * @param title
   * @param releaseDate
   * @param overview
   * @param posterPath
   * @param originalTitle
   * @param voteAverage
   * @param originalLanguage
   * @param adult
   * @param backdropPath
   * @param voteCount
   * @param video
   * @param popularity
   */
  public Result(boolean adult, String backdropPath, List<Integer> genreIds, int id,
                String originalLanguage, String originalTitle, String overview, String releaseDate,
                String posterPath, double popularity, String title, boolean video, double voteAverage,
                int voteCount) {
    super();
    this.adult = adult;
    this.backdropPath = backdropPath;
    this.genreIds = genreIds;
    this.id = id;
    this.originalLanguage = originalLanguage;
    this.original_title = originalTitle;
    this.overview = overview;
    this.release_date = releaseDate;
    this.poster_path = posterPath;
    this.popularity = popularity;
    this.title = title;
    this.video = video;
    this.vote_average = voteAverage;
    this.vote_count = voteCount;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public List<Integer> getGenreIds() {
    return genreIds;
  }

  public void setGenreIds(List<Integer> genreIds) {
    this.genreIds = genreIds;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getOriginalTitle() {
    return original_title;
  }

  public void setOriginalTitle(String originalTitle) {
    this.original_title = originalTitle;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    return release_date;
  }

  public void setReleaseDate(String releaseDate) {
    this.release_date = releaseDate;
  }

  public String getPosterPath() {
    return poster_path;
  }

  public void setPosterPath(String posterPath) {
    this.poster_path = posterPath;
  }

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public double getVoteAverage() {
    return vote_average;
  }

  public void setVoteAverage(double voteAverage) {
    this.vote_average = voteAverage;
  }

  public int getVoteCount() {
    return vote_count;
  }

  public void setVoteCount(int voteCount) {
    this.vote_count = voteCount;
  }
}