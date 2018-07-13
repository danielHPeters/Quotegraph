package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.model.DayQuote
import ch.peters.daniel.quotegraph.model.ECoordinates

/**
 * Coordinates calculator implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class CoordinatesCalculator(private val data: List<DayQuote>) {
  /**
   * Defines the Margin around the graph.
   */
  private val margin = 40

  /**
   * Einzelne Koordinaten für die Linien holen.
   *
   * @param index    Index in ArrayList
   * @param axis     height or width
   * @param coorType unterscheiden zwischen x, x1, y, und y1
   * @return Auf Fenster umgewandelte Koordinatenposition zurückgeben
   */
  fun createCoordinate(index: Int, axis: Double, coorType: ECoordinates): Double {
    val actual: Double
    val max: Double
    val min: Double

    if (coorType == ECoordinates.Y || coorType == ECoordinates.Y1) {
      min = data.minClose()
      max = data.maxClose()
      actual = if (coorType == ECoordinates.Y) data[index - 1].close else data[index].close
    } else if (coorType == ECoordinates.X || coorType == ECoordinates.X1) {
      min = data.minTimeStamp()
      max = data.maxTimeStamp()
      actual = if (coorType == ECoordinates.X) data.timeStamps().get(index - 1) else data[index].quoteDate.time.toDouble()
    }
    return translateToPanel(axis, actual, min, max, coorType == ECoordinates.Y || coorType == ECoordinates.Y1)
  }


  /**
   * Translate data items to panel dimensions.
   * Set inverted to true for y axis because 0 starts at top and not bottom.
   *
   * @param side width or height of panel
   * @param quote     data item from quotes
   * @param max       maximum in quotes lista
   * @param min       minimum in quotes list
   * @param inverted  boolean to check if axis is inverted
   * @return translated point
   */
  private fun translateToPanel(side: Double, quote: Double, max: Double, min: Double, inverted: Boolean): Double {
    val ratio = (side - 2 * margin) / (min - max)
    val temp = (quote - max) * ratio

    return if (inverted) side - temp - margin else temp + margin
  }
}
