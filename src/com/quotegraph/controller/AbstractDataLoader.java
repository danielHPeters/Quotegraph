package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.DayQuote;

import java.util.List;

/**
 * Abstract implementation of DataLoader interface.
 *
 * @author Daniel Peters
 */
abstract class AbstractDataLoader implements DataLoader {
  protected final String defaultDateFormat = "dd.MM.yyyy";
  protected String source;
  protected List<DayQuote> data;
  protected List<Double> timeStamps;
  protected double minClose;
  protected double maxClose;
  protected double minTimeStamp;
  protected double maxTimeStamp;
  protected boolean failed;

  /**
   * Default constructor. Sets the fileName to default.
   *
   * @param source file to be opened
   */
  public AbstractDataLoader(String source) {
    this.failed = false;
    this.source = source;
  }

  /**
   * Get the fileName.
   *
   * @param source file to be loaded
   */
  @Override
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * Get the data List.
   *
   * @return quotes list
   */
  @Override
  public List<DayQuote> getData() {
    return this.data;
  }

  /**
   * Get the minimum close.
   *
   * @return min close value
   */
  @Override
  public double getMinClose() {
    return this.minClose;
  }

  /**
   * Get the maximum close.
   *
   * @return max close value
   */
  @Override
  public double getMaxClose() {
    return this.maxClose;
  }

  /**
   * Get the timestamp list.
   *
   * @return list of all timestamps
   */
  @Override
  public List<Double> getTimeStamps() {
    return this.timeStamps;
  }

  /**
   * Get the lowest value timestamp.
   *
   * @return min timestamp
   */
  @Override
  public double getMinTimeStamp() {
    return this.minTimeStamp;
  }

  /**
   * Get the highest value timestamp.
   *
   * @return max timestamp
   */
  @Override
  public double getMaxTimeStamp() {
    return this.maxTimeStamp;
  }

  /**
   * Boolean flag for loading or source error.
   *
   * @return flag for failure
   */
  @Override
  public boolean hasFailed() {
    return this.failed;
  }

  @Override
  public abstract void load();
}
