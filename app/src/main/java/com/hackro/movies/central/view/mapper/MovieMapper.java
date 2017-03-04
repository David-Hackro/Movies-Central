package com.hackro.movies.central.view.mapper;

import com.hackro.movies.central.data.remote.Mapper;
import com.hackro.movies.central.domain.model.MoviesDomain;
import com.hackro.movies.central.domain.model.ResultDomain;
import com.hackro.movies.central.view.model.MoviesPresentation;
import com.hackro.movies.central.view.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackro on 28/02/17.
 */
public class MovieMapper extends Mapper<MoviesPresentation, MoviesDomain> {

    public static List<MoviesPresentation> transform(List<MoviesDomain> movies) {
        List<MoviesPresentation> moviesPresentations;

        if(movies != null && ! movies.isEmpty()){
            moviesPresentations = new ArrayList<>();
            for (MoviesDomain m : movies){
                moviesPresentations.add(new MoviesPresentation(m.getGenre(),transformResult(m.getMoviesList())));
            }
        }else {
            moviesPresentations = new ArrayList<>();
        }
    return moviesPresentations;
    }

    public static List<Result> transformResult(List<ResultDomain> resultDomain) {
        List<Result> results = new ArrayList<>();
            for (ResultDomain resultD : resultDomain){
                results.add(new Result(
                        resultD.isAdult(),
                        resultD.getBackdropPath(),
                        resultD.getGenreIds(),
                        resultD.getId(),
                        resultD.getOriginalLanguage(),
                        resultD.getOriginal_title(),
                        resultD.getOverview(),
                        resultD.getRelease_date(),
                        resultD.getPoster_path(),
                        resultD.getPopularity(),
                        resultD.getTitle(),
                        resultD.isVideo(),
                        resultD.getVote_average(),
                        resultD.getVote_count()
                ));
            }
        return results;
    }

    @Override
    public MoviesDomain map(MoviesPresentation value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MoviesPresentation reverseMap(MoviesDomain value) {
        return new MoviesPresentation(value.getGenre(),transformResult(value.getMoviesList()));
    }
}
