package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.model.DayQuote
import ch.peters.daniel.quotegraph.model.ECoordinates

/**
 * Coordinates calculator to be implemented for different graphs.
 * May be reworked so only one is needed.
 *
 * @author Daniel Peters
 * @version 1.0
 */
abstract class AbstractCoordinatesCalculator {
  /**
   * Defines the Margin around the graph.
   */
  protected val margin: Int = 40

  /**
   * Get coordinates for lines.
   *
   * @param index    Stelle in der ArrayList
   * @param axis     axis length (width / height)
   * @param coorType differentiate between x, x1, y, und y1
   * @return translated coordinates
   */
  fun createCoordinate(index: Int, axis: Double, coorType: ECoordinates, data: List<DayQuote>): Double {
    var value
    var max
    var min

    if (coorType == ECoordinates.Y || coorType == ECoordinates.Y1) {
      min = data.quotes.getMinClose()
      max = data.getMaxClose()

      value = if (coorType == ECoordinates.Y) data[index - 1].close else data[index].close

    } else {
      min = data.getMinTimeStamp()
      max = data.getMaxTimeStamp()
      value = if (coorType == ECoordinates.X) data.timestamps.get(index - 1) else data.date.get(index).toTimestamp()
    }

    return translateToPanel(axis, value, min, max, coorType == ECoordinates.Y || coorType == ECoordinates.Y1)
  }

  abstract fun translateToPanel(side: Double, value: Double, max: Double, min: Double, inverted: Boolean): Double
}
