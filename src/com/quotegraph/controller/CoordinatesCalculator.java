package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;

/**
 * Coordinates calculator implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class CoordinatesCalculator {

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
   * @param loader data loader instance
   */
  public CoordinatesCalculator(DataLoader loader) {
    this.loader = loader;
  }

  /**
   * Einzelne Koordinaten für die Linien holen.
   *
   * @param index    Index in ArrayList
   * @param axis     height or width
   * @param coorType unterscheiden zwischen x, x1, y, und y1
   * @return Auf Fenster umgewandelte Koordinatenposition zurückgeben
   */
  public double createCoordinate(int index, double axis, ECoordinates coorType) {

    double actual = 0;
    double max = 0;
    double min = 0;

    if (coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1)) {

      min = loader.getMinClose();
      max = loader.getMaxClose();

      if (coorType.equals(ECoordinates.Y)) {

        actual = loader.getData().get(index - 1).getClose();

      } else {

        actual = loader.getData().get(index).getClose();

      }
    } else if (coorType.equals(ECoordinates.X) || coorType.equals(ECoordinates.X1)) {

      min = loader.getMinTimeStamp();
      max = loader.getMaxTimeStamp();

      if (coorType.equals(ECoordinates.X)) {

        actual = loader.getTimeStamps().get(index - 1);
      } else {

        actual = loader.getTimeStamps().get(index);
      }
    }

    return translateToPanel(
        axis,
        actual,
        min,
        max,
        coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1));
  }


  /**
   * Translate data items to panel dimensions.
   * Set inverted to true for y axis because 0 starts at top and not bottom.
   *
   * @param panelSide width or height of panel
   * @param quote     data item from quotes
   * @param max       maximum in quotes lista
   * @param min       minimum in quotes list
   * @param inverted  boolean to check if axis is inverted
   * @return translated point
   */
  public double translateToPanel(
      double panelSide, double quote, double max, double min, boolean inverted) {

    double ratio = (panelSide - 2 * margin) / (min - max);
    double temp = (quote - max) * ratio;

    return Math.round(inverted ? panelSide - temp - margin : temp + margin);
  }

}
