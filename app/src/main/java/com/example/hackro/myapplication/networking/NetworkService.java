package com.example.hackro.myapplication.networking;

import com.example.hackro.myapplication.models.Generos.GenerosResponse;
import com.example.hackro.myapplication.models.Movies.ResponseMovies;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hackro on 19/01/17.
 */
public interface NetworkService {

    @GET("{movie}/{list}")
    Observable<GenerosResponse> getAllGeneros(@Path("movie") String movie, @Path("list") String list, @Query("api_key") String key);

    @GET("{number}/{movies}")
    Observable<ResponseMovies> getAllMovies(@Path("number")int number,@Path("movies")String movies,@Query("api_key")String key, @Query("sort_by")String  sort_by);

}

