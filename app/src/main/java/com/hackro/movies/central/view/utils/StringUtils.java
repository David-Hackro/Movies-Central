package com.hackro.movies.central.view.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hackro on 3/03/17.
 */
public class StringUtils {

    public static Date toDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date");
        }
    }

    public static Double toDouble(String number) {
        try {
            return Double.valueOf(number);
        } catch (Exception e) {
            return 0.0;
        }
    }
}