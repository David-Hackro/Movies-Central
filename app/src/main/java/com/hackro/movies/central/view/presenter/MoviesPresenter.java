package com.hackro.movies.central.view.presenter;

import android.support.annotation.NonNull;

import com.hackro.movies.central.domain.model.MoviesDomain;
import com.hackro.movies.central.domain.usecase.DefaultSubscriber;
import com.hackro.movies.central.domain.usecase.GetMovies;
import com.hackro.movies.central.view.mapper.MovieMapper;
import com.hackro.movies.central.view.model.MoviesPresentation;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hackro on 27/02/17.
 */
public class MoviesPresenter extends Presenter<MoviesPresenter.View> {

    private final GetMovies getMovies;

    @Inject public MoviesPresenter(@NonNull GetMovies getMovies) {
        this.getMovies = getMovies;
    }

    @Override
    public void initialize(){
        super.initialize();
        getView().showLoading();
        getMovies.execute(new MoviesListObserver());
    }

    private final class MoviesListObserver extends DefaultSubscriber<List<MoviesDomain>>{
        @Override
        public void onCompleted() {
            super.onCompleted();
            getView().hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getView().hideLoading();
            getView().showError(e.getMessage());
        }

        @Override
        public void onNext(List<MoviesDomain> movies) {
            super.onNext(movies);
            getView().showMovies(MovieMapper.transform(movies));
        }
    }


    public void destroy(){
        this.getMovies.unsubscribe();
        setView(null);
    }

    public interface View extends Presenter.View{
            void showMovies(List<MoviesPresentation> moviesPresentationList);
    }

}
