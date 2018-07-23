package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.model.DayQuote
import ch.peters.daniel.quotegraph.model.Coordinates
import java.time.ZoneId

/**
 * Coordinates calculator to be implemented for different graphs.
 * May be reworked so only one is needed.
 *
 * @author Daniel Peters
 * @version 1.0
 */
abstract class AbstractCoordinatesCalculator(private val data: List<DayQuote>) {
  private val minClose: Double
  private val maxClose: Double
  private val minTimestamp: Double
  private val maxTimestamp: Double
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
  fun createCoordinate(index: Int, axis: Double, coorType: Coordinates): Double {
    var value: Double
    var max: Double
    var min: Double

    if (coorType == Coordinates.Y || coorType == Coordinates.Y1) {
      min = minClose
      max = maxClose

      value = if (coorType == Coordinates.Y) data[index - 1].close else data[index].close

    } else {
      min = minTimestamp
      max = maxTimestamp
      value = if (coorType == Coordinates.X) {
        data[index - 1].quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli().toDouble()
      } else {
        data[index].quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli().toDouble()
      }
    }

    return translateToPanel(axis, value, min, max, coorType == Coordinates.Y || coorType == Coordinates.Y1)
  }

  abstract fun translateToPanel(side: Double, value: Double, max: Double, min: Double, inverted: Boolean): Double

  init {
    this.minClose = data.map { quote -> quote.close }.min()!!
    this.maxClose = data.map { quote -> quote.close }.max()!!
    this.minTimestamp = data.map { quote -> quote.quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() }.min()!!.toDouble()
    this.maxTimestamp = data.map { quote -> quote.quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() }.max()!!.toDouble()
  }
}
