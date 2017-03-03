package com.hackro.movies.central.data.remote;


import com.hackro.movies.central.data.local.model.GenerosResponse;
import com.hackro.movies.central.data.local.model.ResponseMovies;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by hackro on 27/02/17.
 */
public interface MoviesService {

    @GET("{movie}/{list}")
    Observable<GenerosResponse> getAllGenres(@Path("movie") String movie,
                                             @Path("list") String list, @Query("api_key") String key);

    @GET("{number}/{movies}") Observable<ResponseMovies> getAllMovies(@Path("number") int number,
                                                                      @Path("movies") String movies, @Query("api_key") String key,
                                                                      @Query("sort_by") String sort_by);

}
