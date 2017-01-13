package com.quotegraph.controller;

import java.util.Date;

/**
 *
 * @author d.peters
 */
public class TimestampGenerator {

    /**
     * Generates a timestamp from a Date object
     *
     * @param date the submitted date object
     * @return long containing the timestamp of the submitted date
     */
    public static double dateToTimeStamp(Date date) {

        double timeStamp = Math.round((double) date.getTime() / 1000 / 60 / 60 / 24);
        return timeStamp;
    }
}
