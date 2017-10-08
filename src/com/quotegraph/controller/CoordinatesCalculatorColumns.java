package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;

/**
 * Coordinates calculator for column graph.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class CoordinatesCalculatorColumns extends CoordinatesCalculator {

  /**
   * Default constructor.
   *
   * @param loader data loader instance reference
   */
  public CoordinatesCalculatorColumns(DataLoader loader) {

    super(loader);

  }

  @Override
  public double translateToPanel(
      double panelSide, double value, double max, double min, boolean inverted) {

    double ratio = (panelSide - 2 * margin) / (min - max);
    double temp = (value - max) * ratio;

    return Math.round(inverted ? panelSide - temp - margin : temp + margin);
  }

}
