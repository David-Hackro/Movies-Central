package com.hackro.movies.central.view.activity.home;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hackro.movies.central.MovieApplication;
import com.hackro.movies.central.R;
import com.hackro.movies.central.view.BaseActivity;
import com.hackro.movies.central.view.adapter.MoviesAdapter;
import com.hackro.movies.central.view.model.MoviesView;
import com.hackro.movies.central.view.presenter.MoviesPresenter;

import java.util.List;

import javax.inject.Inject;

public class MoviesActivity extends BaseActivity implements MoviesPresenter.View {

    private ProgressBar progressBar;
    private MoviesAdapter adapter;
    private ExpandableListView moviesView;

    @Inject
    MoviesPresenter presenter;


    @Override
    public void initView() {
        super.initView();
        progressBar = (ProgressBar) findViewById(R.id.loading);
        moviesView = (ExpandableListView) findViewById(R.id.lvExp);
        initializeDagger();
        initializePresenter();
        presenter.initialize();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.movies_activity;
    }


    @Override
    public void showMovies(List<MoviesView> moviesViewList) {
        adapter = new MoviesAdapter(this,moviesViewList);

        moviesView.setAdapter(adapter);

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    private void initializeDagger() {
        MovieApplication app = (MovieApplication) getApplication();
        app.getAppComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

}