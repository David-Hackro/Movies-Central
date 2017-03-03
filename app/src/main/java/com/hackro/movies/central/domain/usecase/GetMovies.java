package com.hackro.movies.central.domain.usecase;

import com.hackro.movies.central.data.repository.AppRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by hackro on 27/02/17.
 */
public class GetMovies extends UseCase {

    private final AppRepository repository;


    @Inject public GetMovies(AppRepository repository) {
        this.repository = repository;
    }


    @Override
    protected Observable buildObservableUseCase() {
        return this.repository.getMovies();
    }
}
