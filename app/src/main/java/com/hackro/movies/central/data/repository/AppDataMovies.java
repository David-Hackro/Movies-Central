package com.hackro.movies.central.data.repository;


import com.hackro.movies.central.domain.model.Movies;

import java.util.List;

import rx.Observable;

/**
 * Created by hackro on 27/02/17.
 */
public interface AppDataMovies {

    Observable<List<Movies>> getMovies();
    
}
