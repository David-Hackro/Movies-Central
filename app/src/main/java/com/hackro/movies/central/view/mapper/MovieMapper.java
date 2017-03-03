package com.hackro.movies.central.view.mapper;

import com.hackro.movies.central.data.remote.Mapper;
import com.hackro.movies.central.domain.model.Movies;
import com.hackro.movies.central.view.model.MoviesView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackro on 28/02/17.
 */
public class MovieMapper extends Mapper<MoviesView, Movies> {

    public static List<MoviesView> transform(List<Movies> movies) {
        List<MoviesView> moviesViews;

        if(movies != null && ! movies.isEmpty()){
            moviesViews = new ArrayList<>();
            for (Movies m : movies){
                moviesViews.add(new MoviesView(m.getGenre(),m.getMovies()));
            }
        }else {
            moviesViews = new ArrayList<>();
        }
    return  moviesViews;
    }

    @Override
    public Movies map(MoviesView value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MoviesView reverseMap(Movies value) {


        return null;
    }
}
