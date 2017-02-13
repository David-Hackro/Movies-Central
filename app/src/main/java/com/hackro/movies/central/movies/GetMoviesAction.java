package com.hackro.movies.central.movies;

import com.hackro.movies.central.BuildConfig;
import com.hackro.movies.central.movies.models.CollectionsMovies;
import com.hackro.movies.central.movies.models.GenerosResponse;
import com.hackro.movies.central.movies.models.ResponseMovies;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

class GetMoviesAction {

  private final MoviesService networkService;

  GetMoviesAction(Retrofit retrofit) {
    this.networkService = retrofit.create(MoviesService.class);
  }

  Observable<List<CollectionsMovies>> request() {
    return networkService.getAllGenres("movie", "list", BuildConfig.KEY_SERVICE)
        .flatMap(userResponse -> Observable.just(userResponse.getGenres()))
        .flatMapIterable(genres -> genres)
        .flatMap(
            genre -> networkService.getAllMovies(genre.getId(), "movies", BuildConfig.KEY_SERVICE,
                "created_at.asc"), CollectionsMovies::new)
        .toList();
  }

  interface MoviesService {

    @GET("{movie}/{list}") Observable<GenerosResponse> getAllGenres(@Path("movie") String movie,
        @Path("list") String list, @Query("api_key") String key);

    @GET("{number}/{movies}") Observable<ResponseMovies> getAllMovies(@Path("number") int number,
        @Path("movies") String movies, @Query("api_key") String key,
        @Query("sort_by") String sort_by);
  }
}
