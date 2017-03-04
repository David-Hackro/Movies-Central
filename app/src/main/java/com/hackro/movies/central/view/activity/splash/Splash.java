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
  private static final int animCircularRevealDuration = 0;
  private static final int animLogoSplashDuration = 800;
  private static final int originalHeight= 500;
  private static final int originalWidth = 500;
  private static final int animPathStrokeDrawingDuration = 1500;
  private static final int pathSplashStrokeSize = 3;
  private static final int animPathFillingDuration = 1500;
  private static final float textSize = 30f;
  private static final int animTitleDuration = 1000;

  @Override public void initSplash(ConfigSplash configSplash) {
    getSupportActionBar().hide();
    initialize(configSplash);
  }
  public void initialize(ConfigSplash configSplash){
    configSplash.setBackgroundColor(R.color.colorAccent);
    configSplash.setAnimCircularRevealDuration(animCircularRevealDuration);
    configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
    configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

    configSplash.setLogoSplash(R.mipmap.ic_launcher);
    configSplash.setAnimLogoSplashDuration(animLogoSplashDuration);
    configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);

    configSplash.setPathSplash(DroidLogo.PATH);
    configSplash.setOriginalHeight(originalHeight);
    configSplash.setOriginalWidth(originalWidth);
    configSplash.setAnimPathStrokeDrawingDuration(animPathStrokeDrawingDuration);
    configSplash.setPathSplashStrokeSize(pathSplashStrokeSize);
    configSplash.setPathSplashStrokeColor(R.color.strokeColor);
    configSplash.setAnimPathFillingDuration(animPathFillingDuration);
    configSplash.setPathSplashFillColor(R.color.colorPrimary);

    configSplash.setTitleSplash(getResources().getString(R.string.app_name));
    configSplash.setTitleTextColor(R.color.colorPrimaryDark);
    configSplash.setTitleTextSize(textSize);
    configSplash.setAnimTitleDuration(animTitleDuration);
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
  private void showHome()
  {
    finish();
    startActivity(new Intent(this, MoviesActivity.class));
  }
}