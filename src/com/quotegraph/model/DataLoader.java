package com.quotegraph.model;

import java.util.List;

/**
 * Interface for data loading classes
 *
 * @author Daniel Peters
 * @version 1.0
 */
public interface DataLoader {
  void load();

  void setSource(String source);

  List<DayQuote> getData();

  double getMinClose();

  double getMaxClose();

  List<Double> getTimeStamps();

  double getMinTimeStamp();

  double getMaxTimeStamp();

  boolean hasFailed();
}
