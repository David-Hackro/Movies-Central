package com.example.hackro.myapplication.Splash;


import android.app.Activity;
import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.example.hackro.myapplication.R;
import com.example.hackro.myapplication.home.MainActivity;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class Splash extends AwesomeSplash {

    private long ms=0;
    private long splashTime=1000;
    private boolean splashActive = true;

    @Override
    public void initSplash(ConfigSplash configSplash) {

        getSupportActionBar().hide();

        configSplash.setBackgroundColor(R.color.colorAccent);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(800); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
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

    @Override
    public void animationsFinished() {
        final Activity a = this;

        Thread mythread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        ms=ms+1000;
                        sleep(1000);
                    }
                } catch(Exception e) {}
                finally {
                    Intent intent = new Intent(a.getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
        };
        mythread.start();
    }
}