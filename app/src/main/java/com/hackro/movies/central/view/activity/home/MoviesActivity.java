package com.hackro.movies.central.view.activity.home;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hackro.movies.central.MovieApplication;
import com.hackro.movies.central.view.BaseActivity;
import com.hackro.movies.central.view.adapter.MoviesAdapter;
import com.hackro.movies.central.view.model.MoviesPresentation;
import com.hackro.movies.central.view.model.Result;
import com.hackro.movies.central.view.presenter.MoviesPresenter;
import com.hackro.moviesDomain.central.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MoviesActivity extends BaseActivity implements MoviesPresenter.View {

    @BindView(R.id.loading)
    ProgressBar progressBar;
    @BindView(R.id.lvExp)
    ExpandableListView moviesView;
    private MoviesAdapter adapter;
    private MovieDetail detail;

    @Inject
    MoviesPresenter presenter;


    @Override
    public void initView() {
        super.initView();
        initializeView();
        initializeDagger();
        initializePresenter();
        presenter.initialize();
    }

    private void initializeView() {
        detail = new MovieDetail(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.movies_activity;
    }

    @Override
    public void showMovies(List<MoviesPresentation> moviesPresentationList) {
        adapter = new MoviesAdapter(this, moviesPresentationList);
        moviesView.setAdapter(adapter);

        moviesView.setOnChildClickListener(
                (expandableListView, view, groupPosition, childPosition, listener) -> {
                    Result movie = (Result) adapter.getChild(groupPosition, childPosition);
                    detail.showDetail(movie);
                    return false;
                });
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
    public void showError(String message) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show();}

    private void initializeDagger() {
        MovieApplication app = (MovieApplication) getApplication();
        app.getAppComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_order_alphabetically:
                    adapter.orderAlphabetically();
                    return true;
                case R.id.action_order_date:
                    if (adapter!= null) adapter.orderDate();
                    return true;
                case R.id.action_order_stars:
                    if (adapter!= null) adapter.orderStars();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
    }
}