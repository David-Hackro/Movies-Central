package com.example.hackro.myapplication.models.Movies;

import java.util.List;

public class ResponseMovies {

    private int id;
    private int page;
    private List<Result> results = null;
    private int totalPages;
    private int totalResults;

    /**
     * No args constructor for use in serialization
     */
    public ResponseMovies() {
    }

    /**
     * @param id
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public ResponseMovies(int id, int page, List<Result> results, int totalPages, int totalResults) {
        super();
        this.id = id;
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

}
