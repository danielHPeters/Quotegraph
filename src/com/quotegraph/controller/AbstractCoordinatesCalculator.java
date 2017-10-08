package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;

/**
 * Coordinates calculator to be implemented for different graphs.
 * May be reworked so only one is needed.
 *
 * @author Daniel Peters
 */
public abstract class AbstractCoordinatesCalculator {
  /**
   * Defines the Margin around the graph.
   */
  protected final int margin = 40;

  /**
   * Reference to the DataLoader object.
   */
  protected final DataLoader loader;

  /**
   * Default constructor.
   *
   * @param loader instance of a data loader
   */
  public AbstractCoordinatesCalculator(DataLoader loader) {
    this.loader = loader;
  }

  /**
   * Get coordinates for lines.
   *
   * @param index    Stelle in der ArrayList
   * @param axis     axis length (width / height)
   * @param coorType differentiate between x, x1, y, und y1
   * @return translated coordinates
   */
  public double createCoordinate(int index, double axis, ECoordinates coorType) {

    double actualVal;
    double maxVal;
    double minVal;

    if (coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1)) {
      minVal = loader.getMinClose();
      maxVal = loader.getMaxClose();

      actualVal = coorType.equals(ECoordinates.Y)
          ? loader.getData().get(index - 1).getClose()
          : loader.getData().get(index).getClose();

    } else {

      minVal = loader.getMinTimeStamp();
      maxVal = loader.getMaxTimeStamp();

      actualVal = coorType.equals(ECoordinates.X)
          ? loader.getTimeStamps().get(index - 1)
          : loader.getTimeStamps().get(index);
    }

    return translateToPanel(
        axis,
        actualVal,
        minVal,
        maxVal,
        coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1));
  }

  public abstract double translateToPanel(double panelDimension, double quote,
                                          double maxQuote, double minQuote, boolean inverted);
}
