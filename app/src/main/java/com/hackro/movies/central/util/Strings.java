package com.hackro.movies.central.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Strings {

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
