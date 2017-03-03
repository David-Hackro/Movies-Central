package com.hackro.movies.central.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hackro on 28/02/17.
 */
public abstract class  BaseActivity extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    public void initView() {
    }
    protected abstract int getLayoutId();


    public void bindView(){
    }
}
