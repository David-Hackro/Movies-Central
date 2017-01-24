package com.example.hackro.myapplication.networking;


import android.util.Log;

import com.example.hackro.myapplication.BuildConfig;
import com.example.hackro.myapplication.models.CollectionsMovies;
import com.example.hackro.myapplication.models.Generos.GenerosResponse;
import com.example.hackro.myapplication.models.Generos.Genre;
import com.example.hackro.myapplication.models.Movies.ResponseMovies;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by hackro on 19/01/17.
 */
public class Service {

    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    private Observable<GenerosResponse> makeRequestToServiceA() {

        return  networkService.getAllGeneros("movie","list",BuildConfig.keyService); //some network call
    }

    private Observable<ResponseMovies> makeRequestToServiceB(Genre genre) {
        return networkService.getAllMovies(genre.getId(),"movies",BuildConfig.keyService,"created_at.asc"); //some network call based on response from ServiceA
    }


    public Subscription getCityList(final GetCityListCallback callback) {

        List<CollectionsMovies> collectionList = new ArrayList<>();
           return makeRequestToServiceA()
                .flatMap(userResponse -> Observable.just(userResponse.getGenres()))      //get list from response
                .flatMapIterable(baseDatas -> baseDatas)
                .flatMap(new Func1<Genre, Observable<? extends ResponseMovies>>() {

                    @Override
                    public Observable<? extends ResponseMovies> call(Genre genre) {
                        return makeRequestToServiceB(genre);
                    }
                }, new Func2<Genre, ResponseMovies, CollectionsMovies>() {

                    @Override
                    public CollectionsMovies call(Genre genre, ResponseMovies responseMovies) {
                        return new CollectionsMovies(genre,responseMovies);
                    }
                }).
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CollectionsMovies>() {
                    @Override
                    public void onCompleted() {
                        callback.onSuccess(collectionList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Ha ocurrido un error,",e.getMessage());
                    }

                    @Override
                    public void onNext(CollectionsMovies collectionsMovies) {
                        collectionList.add(collectionsMovies);
                    }
                });

    }


    public interface GetCityListCallback {
        void onSuccess(List<CollectionsMovies> collectionList);

        void onError(NetworkError networkError);
    }
}
