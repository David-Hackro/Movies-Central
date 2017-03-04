package com.hackro.movies.central.view.activity.splash;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.hackro.movies.central.view.activity.home.MoviesActivity;
import com.hackro.moviesDomain.central.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AwesomeSplash {

  private static final int SPLASH_TIME = 1000;

  @Override public void initSplash(ConfigSplash configSplash) {
    getSupportActionBar().hide();
    initialize(configSplash);
  }
  public void initialize(ConfigSplash configSplash){
    configSplash.setBackgroundColor(R.color.colorAccent);
    configSplash.setAnimCircularRevealDuration(0);
    configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
    configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

    configSplash.setLogoSplash(R.mipmap.ic_launcher);
    configSplash.setAnimLogoSplashDuration(800);
    configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);

    configSplash.setPathSplash(DroidLogo.PATH);
    configSplash.setOriginalHeight(500);
    configSplash.setOriginalWidth(500);
    configSplash.setAnimPathStrokeDrawingDuration(1500);
    configSplash.setPathSplashStrokeSize(3);
    configSplash.setPathSplashStrokeColor(R.color.strokeColor);
    configSplash.setAnimPathFillingDuration(1500);
    configSplash.setPathSplashFillColor(R.color.colorPrimary);

    configSplash.setTitleSplash(getResources().getString(R.string.app_name));
    configSplash.setTitleTextColor(R.color.colorPrimaryDark);
    configSplash.setTitleTextSize(30f);
    configSplash.setAnimTitleDuration(1000);
    configSplash.setAnimTitleTechnique(Techniques.FlipInX);
  }
  @Override public void animationsFinished() {
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
      @Override public void run() {
        showHome();
      }
    };
    timer.schedule(timerTask, SPLASH_TIME);
  }
  private void showHome() {
    startActivity(new Intent(this, MoviesActivity.class));
  }
}