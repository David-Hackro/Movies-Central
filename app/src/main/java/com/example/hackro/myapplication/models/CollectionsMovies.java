package com.example.hackro.myapplication.models;

import com.example.hackro.myapplication.models.Generos.Genre;
import com.example.hackro.myapplication.models.Movies.ResponseMovies;

/**
 * Created by hackro on 21/01/17.
 */
public class CollectionsMovies {

    public Genre genres;
    public ResponseMovies movies;

    public CollectionsMovies(Genre genres, ResponseMovies movies) {
        this.genres = genres;
        this.movies = movies;
    }
}
