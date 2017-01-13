package com.quotegraph.controller;

import java.util.Date;

/**
 *
 * @author d.peters
 */
public class TimestampGenerator {

    /**
     *
     * @param date
     * @return Gibt das Datum als long in Form von einem Unix Timestamp zurück.
     */
    public static double dateToTimeStamp(Date date) {

        double timeStamp = Math.round((double) date.getTime() / 1000 / 60 / 60 / 24);
        return timeStamp;
    }
}
