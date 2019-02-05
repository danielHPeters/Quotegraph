package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.model.DayQuote
import ch.peters.daniel.quotegraph.model.Coordinates
import java.time.ZoneId

/**
 * Coordinates calculator implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class CoordinatesCalculator(private val data: List<DayQuote>) {
  private var minClose: Double
  private var maxClose: Double
  private var minTimestamp: Double
  private var maxTimestamp: Double
  /**
   * Defines the Margin around the graph.
   */
  private val margin = 40

  /**
   * Create a single coordinate based on passed data.
   *
   * @param index    Index in ArrayList
   * @param axis     height or width
   * @param coordinate Types: x, x1, y, und y1
   * @return Transformed coordinate
   */
  fun createCoordinate(index: Int, axis: Double, coordinate: Coordinates): Double {
    var actual = 0.0
    var max = 0.0
    var min = 0.0

    if (coordinate == Coordinates.Y || coordinate == Coordinates.Y1) {
      min = minClose
      max = maxClose
      actual = if (coordinate == Coordinates.Y) data[index - 1].close else data[index].close
    } else if (coordinate == Coordinates.X || coordinate == Coordinates.X1) {
      min = minTimestamp
      max = maxTimestamp
      actual = if (coordinate == Coordinates.X) {
        data[index - 1].quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli().toDouble()
      } else {
        data[index].quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli().toDouble()
      }
    }
    return translateToPanel(axis, actual, min, max, coordinate == Coordinates.Y || coordinate == Coordinates.Y1)
  }


  /**
   * Translate data items to panel dimensions.
   * Set inverted to true for y axis because 0 starts at top and not bottom.
   *
   * @param side width or height of panel
   * @param quote     data item from quotes
   * @param max       maximum in quotes list
   * @param min       minimum in quotes list
   * @param inverted  boolean to check if axis is inverted
   * @return translated point
   */
  private fun translateToPanel(side: Double, quote: Double, max: Double, min: Double, inverted: Boolean): Double {
    val ratio = (side - 2 * margin) / (min - max)
    val temp = (quote - max) * ratio

    return if (inverted) side - temp - margin else temp + margin
  }

  init {
    this.minClose = data.map { quote -> quote.close }.min()!!
    this.maxClose = data.map { quote -> quote.close }.max()!!
    this.minTimestamp = data.map { quote -> quote.quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() }.min()!!.toDouble()
    this.maxTimestamp = data.map { quote -> quote.quoteDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() }.max()!!.toDouble()
  }
}
