package com.hackro.movies.central.data.remote;

import android.support.annotation.NonNull;

import com.hackro.movies.central.BuildConfig;
import com.hackro.movies.central.data.local.model.CollectionsMovies;
import com.hackro.movies.central.data.repository.DataSource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by hackro on 27/02/17.
 */
public class AppRemoteData  implements DataSource {


    private Retrofit retrofit;

    @Inject
    public AppRemoteData(@NonNull Retrofit retrofit) {

        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<CollectionsMovies>> getMovies() {
        return retrofit.create(MoviesService.class)
                .getAllGenres("movie", "list", BuildConfig.KEY_SERVICE)
                .flatMap(userResponse -> Observable.just(userResponse.getGenres()))
                .flatMapIterable(genres -> genres)
                .flatMap(
                        genre -> {
                            return retrofit.create(MoviesService.class).getAllMovies(genre.getId(), "movies", BuildConfig.KEY_SERVICE,
                                    "created_at.asc");
                        }, CollectionsMovies::new)
                .toList();
    }
}
