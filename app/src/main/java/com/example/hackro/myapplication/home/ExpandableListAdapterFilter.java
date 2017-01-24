package com.example.hackro.myapplication.home;

/**
 * Created by hackro on 22/01/17.
 */
public interface ExpandableListAdapterFilter {

    void orderAlphabetically();

    void orderDate();

    void orderStars();

    void findName(String name);

    void findStars(Double vote_average);

    void resetFilters();

}
