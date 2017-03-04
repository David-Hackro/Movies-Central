package com.hackro.movies.central.view.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hackro.movies.central.view.model.Result;
import com.hackro.moviesDomain.central.BuildConfig;
import com.hackro.moviesDomain.central.R;


class MovieDetail {

  private Dialog dialogView;
  private TextView title;
  private TextView txt_release_date;
  private TextView txt_popularity;
  private TextView txt_vote_count;
  private TextView txt_overview;
  private RatingBar bar_vote_average;
  private ImageView Backdrop;

  MovieDetail(Context context) {
    dialogView = new Dialog(context, R.style.Theme_Dialog_Translucent);
    dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialogView.setContentView(R.layout.movie_detail_dialog);
    title = (TextView) dialogView.findViewById(R.id.title);
    txt_release_date = (TextView) dialogView.findViewById(R.id.txt_release_date);
    txt_popularity = (TextView) dialogView.findViewById(R.id.txt_popularity);
    txt_vote_count = (TextView) dialogView.findViewById(R.id.txt_vote_count);
    txt_overview = (TextView) dialogView.findViewById(R.id.txt_overview);
    bar_vote_average = (RatingBar) dialogView.findViewById(R.id.bar_vote_average);
    Backdrop = (ImageView) dialogView.findViewById(R.id.backdrop_path);
  }

  void showDetail(Result result) {
    title.setText(result.getTitle());
    txt_release_date.setText(result.getReleaseDate());
    txt_popularity.setText(String.valueOf(result.getPopularity()));
    txt_vote_count.setText(String.valueOf(result.getVoteCount()));
    txt_overview.setText(result.getOverview());
    bar_vote_average.setRating(Float.valueOf(String.valueOf(result.getVoteAverage() / 2)));
    String pathImage = BuildConfig.IMAGE_PATH + result.getPosterPath();

    Glide.with(dialogView.getContext())
        .load(pathImage)
        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        .placeholder(R.mipmap.imagedefault)
        .skipMemoryCache(true)
        .into(Backdrop);
    dialogView.show();
  }
}
