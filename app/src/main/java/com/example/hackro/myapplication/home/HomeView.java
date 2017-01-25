package com.example.hackro.myapplication.home;

import com.example.hackro.myapplication.models.Collections.CollectionsMovies;

import java.util.List;

/**
 * Created by hackro on 19/01/17.
 */
public interface HomeView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getityListSuccess(List<CollectionsMovies> collectionList);
}
