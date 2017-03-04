package com.hackro.movies.central.data.repository;

import com.hackro.movies.central.data.local.model.CollectionsMovies;
import com.hackro.movies.central.data.remote.Mapper;
import com.hackro.movies.central.domain.model.MoviesDomain;
import com.hackro.movies.central.domain.model.ResultDomain;
import com.hackro.movies.central.view.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackro on 3/03/17.
 */
public class MovieToMovieEntityMapper extends Mapper<MoviesDomain,CollectionsMovies> {


    @Override
    public CollectionsMovies map(MoviesDomain value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MoviesDomain reverseMap(CollectionsMovies value) {
        MoviesDomain moviesDomain = new MoviesDomain();
        moviesDomain.setGenre(value.getGenre().getName());
        List<ResultDomain> resultDomains = new ArrayList<>();

        for (Result result : value.getMovies().getResults()){
            resultDomains.add(new ResultDomain(
                    result.isAdult(),
                    result.getBackdropPath(),
                    result.getGenreIds(),
                    result.getId(),
                    result.getOriginalLanguage(),
                    result.getOriginalTitle(),
                    result.getOverview(),
                    result.getReleaseDate(),
                    result.getPosterPath(),
                    result.getPopularity(),
                    result.getTitle(),
                    result.isVideo(),
                    result.getVoteAverage(),
                    result.getVoteCount()
            ));
        }

        moviesDomain.setMoviesList(resultDomains);
        return moviesDomain;
    }


}
