package com.hackro.movies.central.movies;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.hackro.movies.central.R;
import com.hackro.movies.central.movies.models.CollectionsMovies;
import com.hackro.movies.central.movies.models.Genre;
import com.hackro.movies.central.movies.models.Result;
import com.hackro.movies.central.util.BaseApp;
import com.hackro.movies.central.util.Strings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;

public class MoviesActivity extends BaseApp implements MoviesView {

  @Inject public GetMoviesAction getMoviesAction;

  private ProgressBar loadingView;

  private MoviesAdapter moviesAdapter;
  private ExpandableListView moviesView;
  private List<Genre> genres = new ArrayList<>();
  private HashMap<Genre, List<Result>> genresMap = new HashMap<>();
  private MoviesPresenter presenter;
  private MovieDetail detail;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    setContentView(R.layout.movies_activity);

    getMainComponent().inject(this);
    moviesView = (ExpandableListView) findViewById(R.id.lvExp);
    loadingView = (ProgressBar) findViewById(R.id.loading);
    detail = new MovieDetail(this);
    presenter = new MoviesPresenter(getMoviesAction, this);
    presenter.getCityList();
  }

  @Override public void showLoadingView() {
    loadingView.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoadingView() {
    loadingView.setVisibility(View.GONE);
  }

  @Override public void showFailureMessage(Throwable error) {
    @StringRes int errorMessage = R.string.movies_default_error;
    if (error instanceof IOException) {
      errorMessage = R.string.movies_network_error;
    }
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
  }

  @Override public void showMoviesView(List<CollectionsMovies> collectionsMoviesList) {
    for (CollectionsMovies collection : collectionsMoviesList) {
      genres.add(collection.getGenre());
      genresMap.put(collection.getGenre(), collection.getMovies().getResults());
    }

    moviesAdapter = new MoviesAdapter(this, genres, genresMap);
    moviesView.setAdapter(moviesAdapter);

    moviesView.setOnChildClickListener(
        (expandableListView, view, groupPosition, childPosition, listener) -> {
          Result movie = (Result) moviesAdapter.getChild(groupPosition, childPosition);
          detail.showDetail(movie);
          return false;
        });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.movies_menu, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.action_buscar).getActionView();
    SearchView searchViewStars = (SearchView) menu.findItem(R.id.action_find_stars).getActionView();

    searchView.setQueryHint(getResources().getString(R.string.action_search_title));
    if (null != searchView) {
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
      searchView.setIconifiedByDefault(false);
    }

    searchViewStars.setQueryHint(getResources().getString(R.string.action_search_stars));
    if (null != searchViewStars) {
      searchViewStars.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
      searchViewStars.setIconifiedByDefault(false);
    }

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override public boolean onQueryTextChange(String newText) {
        if (moviesAdapter != null) {
          if (!newText.trim().isEmpty()) {
            moviesAdapter.resetFilters();
            moviesAdapter.findName(newText);
          } else {
            moviesAdapter.resetFilters();
          }
        }

        return false;
      }
    });

    searchViewStars.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override public boolean onQueryTextChange(String newText) {
        if (moviesAdapter != null) {
          if (!newText.trim().isEmpty()) {
            moviesAdapter.resetFilters();
            moviesAdapter.findStars(Strings.toDouble(newText));
          } else {
            moviesAdapter.resetFilters();
          }
        }
        return false;
      }
    });

    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_order_alphabetically:
        if (moviesAdapter != null) moviesAdapter.orderAlphabetically();
        return true;
      case R.id.action_order_date:
        if (moviesAdapter != null) moviesAdapter.orderDate();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.destroy();
  }
}