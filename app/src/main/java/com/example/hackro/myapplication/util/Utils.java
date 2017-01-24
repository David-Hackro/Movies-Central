package com.example.hackro.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hackro on 22/01/17.
 */
public class Utils {


    public static Date convertStringtoDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double converttoDouble(String number){
        try {
            return Double.valueOf(number);
        }catch (Exception e){
            return 0.0;
        }
    }
}
