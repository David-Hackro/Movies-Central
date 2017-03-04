package com.hackro.movies.central.data.repository;

import android.support.annotation.NonNull;

import com.hackro.movies.central.data.remote.AppRemoteData;
import com.hackro.movies.central.domain.model.MoviesDomain;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by hackro on 27/02/17.
 */
public class AppRepository implements AppDataMovies{


    private AppRemoteData remoteData;
    private MovieToMovieEntityMapper mapper;

    @Inject
    public AppRepository(@NonNull AppRemoteData remoteData) {
        this.remoteData = remoteData;
        mapper = new MovieToMovieEntityMapper();
    }


    @Override
    public Observable<List<MoviesDomain>> getMovies() {

        return remoteData.getMovies().map(collectionsMovies ->
                mapper.reverseMap(collectionsMovies));
            //this line is if you need implement persistence of data using db
        /*Observable.concat(remoteData.getMovies(),remoteData.getMovies())
                .first(collectionsMovies -> collectionsMovies != null);*/
    }
}
