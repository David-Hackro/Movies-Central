package com.example.hackro.myapplication.deps;

import com.example.hackro.myapplication.home.MainActivity;
import com.example.hackro.myapplication.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hackro on 19/01/17.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface Deps {
    void inject(MainActivity MainActivity);
}
