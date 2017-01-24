package com.example.hackro.myapplication.home;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hackro.myapplication.BuildConfig;
import com.example.hackro.myapplication.R;
import com.example.hackro.myapplication.models.CollectionsMovies;
import com.example.hackro.myapplication.models.Generos.Genre;
import com.example.hackro.myapplication.models.Movies.ResponseMovies;
import com.example.hackro.myapplication.models.Movies.Result;
import com.example.hackro.myapplication.networking.Service;
import com.example.hackro.myapplication.util.BaseApp;
import com.example.hackro.myapplication.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends BaseApp implements HomeView {


    @Inject
    public Service service;
    private ProgressBar progressBar;
    private Dialog dglLoad;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<Genre> listDataHeader;
    private  HashMap<Genre, List<Result>> listDataChild;

    private TextView title;
    private TextView txt_release_date;
    private TextView txt_popularity;
    private TextView txt_vote_count;
    private TextView txt_overview;
    private RatingBar bar_vote_average;
    private ImageView Backdrop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        listDataHeader = new ArrayList<Genre>();
        listDataChild = new HashMap<Genre, List<Result>>();
        getDeps().inject(this);
        initDialog();
        HomePresenter presenter = new HomePresenter(service, this);
        presenter.getCityList();


    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(this, appErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getityListSuccess(List<CollectionsMovies> collectionsMoviesList) {
        for (CollectionsMovies collection : collectionsMoviesList){
            listDataHeader.add(collection.genres);
                listDataChild.put(collection.genres,collection.movies.getResults());
        }

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Result r = (Result) listAdapter.getChild(i, i1);
                showDetail(r);
                return false;
            }
        });
    }


    private void showDetail(Result r) {
        title.setText(r.getTitle());
        txt_release_date.setText(r.getReleaseDate());
        txt_popularity.setText(String.valueOf(r.getPopularity()));
        txt_vote_count.setText(String.valueOf(r.getVoteCount()));
        txt_overview.setText(r.getOverview());
        bar_vote_average.setRating(Float.valueOf(String.valueOf(r.getVoteAverage() / 2)));
        String pathImage = BuildConfig.Imagepath + r.getPosterPath();


        Glide.with(dglLoad.getContext())
                .load(pathImage)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(Backdrop);
        dglLoad.show();
    }

    private void initDialog() {
        dglLoad = new Dialog(this, R.style.Theme_Dialog_Translucent);
        dglLoad.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dglLoad.setContentView(R.layout.dialog_details);
        title = (TextView) dglLoad.findViewById(R.id.title);
        txt_release_date = (TextView) dglLoad.findViewById(R.id.txt_release_date);
        txt_popularity = (TextView) dglLoad.findViewById(R.id.txt_popularity);
        txt_vote_count = (TextView) dglLoad.findViewById(R.id.txt_vote_count);
        txt_overview = (TextView) dglLoad.findViewById(R.id.txt_overview);
        bar_vote_average = (RatingBar) dglLoad.findViewById(R.id.bar_vote_average);
        Backdrop = (ImageView) dglLoad.findViewById(R.id.backdrop_path);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.trim().isEmpty()) {
                    listAdapter.resetFilters();
                    listAdapter.findName(newText);
                } else
                    listAdapter.resetFilters();
                return false;
            }
        });



        searchViewStars.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.trim().isEmpty()) {
                    listAdapter.resetFilters();
                    listAdapter.findStars(Utils.converttoDouble(newText));
                } else
                    listAdapter.resetFilters();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_alphabetically:
                listAdapter.orderAlphabetically();
                return true;
            case R.id.action_order_date:
                listAdapter.orderDate();
                return true;

            case R.id.action_order_stars:
                listAdapter.orderStars();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}