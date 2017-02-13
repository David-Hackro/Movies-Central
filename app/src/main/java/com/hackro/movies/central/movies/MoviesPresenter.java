package com.hackro.movies.central.movies;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class MoviesPresenter {
  private final GetMoviesAction getMoviesAction;
  private final MoviesView view;
  private CompositeSubscription subscriptions;

  MoviesPresenter(GetMoviesAction getMoviesAction, MoviesView view) {
    this.getMoviesAction = getMoviesAction;
    this.view = view;
    this.subscriptions = new CompositeSubscription();
  }

  void getCityList() {
    view.showLoadingView();

    Subscription subscription = getMoviesAction.request()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(collectionsMovies -> {
          view.hideLoadingView();
          if (collectionsMovies.size() >= 6) view.showMoviesView(collectionsMovies);
        }, throwable -> {
          view.hideLoadingView();
          view.showFailureMessage(throwable);
        });

    subscriptions.add(subscription);
  }

  void destroy() {
    subscriptions.unsubscribe();
  }
}