package com.quotegraph.model;

import java.util.Date;

/**
 * Represents data from a daily quote as an object.
 *
 * @author Daniel Peters
 */
public class DayQuote implements Comparable<DayQuote> {

  /**
   * Date of the quote.
   */
  private final Date quoteDate;

  /**
   * Opening value of the day.
   */
  private final double open;

  /**
   * Highest value of the day.
   */
  private final double high;

  /**
   * Lowest value of the day.
   */
  private final double low;

  /**
   * Closing value of the day.
   */
  private final double close;

  /**
   * Default constructor which initializes all attributes.
   *
   * @param quoteDate date
   * @param open open value
   * @param high highest value
   * @param low lowest value
   * @param close close value
   */
  public DayQuote(Date quoteDate, double open, double high, double low, double close) {

    this.quoteDate = quoteDate;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
  }

  public Date getQuoteDate() {
    return quoteDate;
  }

  public double getOpen() {
    return open;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public Double getClose() {
    return close;
  }

  @Override
  public int compareTo(DayQuote o) {
    return Double.compare(this.close, o.getClose());
  }
}
