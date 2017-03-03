package com.hackro.movies.central.data.repository;

import com.hackro.movies.central.data.local.model.CollectionsMovies;

import java.util.List;

import rx.Observable;

/**
 * Created by hackro on 3/03/17.
 */
public interface DataSource {
    Observable<List<CollectionsMovies>> getMovies();

}
