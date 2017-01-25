package com.example.hackro.myapplication.home;

import com.example.hackro.myapplication.models.CollectionsMovies;
import com.example.hackro.myapplication.networking.NetworkError;
import com.example.hackro.myapplication.networking.Service;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hackro on 19/01/17.
 */
public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {

            @Override
            public void onSuccess(List<CollectionsMovies> collectionList) {
                view.removeWait();
                if(collectionList.size()>=6)
                    view.getityListSuccess(collectionList);


            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}