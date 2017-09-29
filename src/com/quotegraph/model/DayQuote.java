package com.quotegraph.model;

import java.util.Date;

/**
 * Represents data from a daily quote as an object
 *
 * @author d.peters
 */
public class DayQuote implements Comparable<DayQuote> {

    /**
     * Date of the quote
     */
    private final Date quoteDate;

    /**
     * Opening value of the day
     */
    private final double open;

    /**
     * Highest value of the day
     */
    private final double high;

    /**
     * Lowest value of the day
     */
    private final double low;

    /**
     * Closing vaulue of the day
     */
    private final double close;

    /**
     * Default constructor which initializes all attributes
     *
     * @param quoteDate
     * @param open
     * @param high
     * @param low
     * @param close
     */
    public DayQuote(Date quoteDate, double open, double high, double low, double close) {

        this.quoteDate = quoteDate;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    /**
     * @return
     */
    public Date getQuoteDate() {
        return quoteDate;
    }

    /**
     * @return
     */
    public double getOpen() {
        return open;
    }

    /**
     * @return
     */
    public double getHigh() {
        return high;
    }

    /**
     * @return
     */
    public double getLow() {
        return low;
    }

    /**
     * @return
     */
    public Double getClose() {
        return close;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public int compareTo(DayQuote o) {
        return Double.compare(this.close, o.getClose());
    }

}
