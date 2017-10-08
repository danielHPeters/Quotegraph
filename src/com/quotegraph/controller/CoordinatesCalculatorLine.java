package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;

/**
 * Scales coordinates to the window to allow for resizing of graph.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class CoordinatesCalculatorLine extends AbstractCoordinatesCalculator {
  /**
   * Default constructor.
   *
   * @param loader data loader instance reference
   */
  public CoordinatesCalculatorLine(DataLoader loader) {
    super(loader);
  }

  /**
   * Translate data items to panel dimensions.
   * Set inverted to true for y axis because 0 starts at top and not bottom.
   *
   * @param panelSide width or height of panel
   * @param quote     data item from quotes
   * @param max       maximum in quotes list
   * @param min       minimum in quotes list
   * @param inverted  boolean to check if axis is inverted
   * @return translated point
   */
  @Override
  public double translateToPanel(
      double panelSide, double quote, double max, double min, boolean inverted) {

    double ratio = (panelSide - 2 * margin) / (min - max);
    double temp = (quote - max) * ratio;

    return Math.round(inverted ? panelSide - temp - margin : temp + margin);
  }
}
