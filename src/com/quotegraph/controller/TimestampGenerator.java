package com.quotegraph.controller;

import java.util.Date;

/**
 * Generates timestamps from dates.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class TimestampGenerator {
  /**
   * Generates a timestamp from a Date object.
   *
   * @param date the submitted date object
   * @return long containing the timestamp of the submitted date
   */
  public static double dateToTimeStamp(Date date) {
    return Math.round((double) date.getTime() / 1000 / 60 / 60 / 24);
  }
}
