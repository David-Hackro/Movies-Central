package com.hackro.movies.central.data.repository;

import com.hackro.movies.central.data.local.model.CollectionsMovies;
import com.hackro.movies.central.data.remote.Mapper;
import com.hackro.movies.central.domain.model.Movies;

/**
 * Created by hackro on 3/03/17.
 */
public class MovieToMovieEntityMapper extends Mapper<Movies,CollectionsMovies> {


    @Override
    public CollectionsMovies map(Movies value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Movies reverseMap(CollectionsMovies value) {
        Movies movies = new Movies();
        movies.setMovies(value.getMovies());
        movies.setGenre(value.getGenre());
        return movies;
    }
}
