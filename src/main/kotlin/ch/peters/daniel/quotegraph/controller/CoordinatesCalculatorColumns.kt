package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.model.DayQuote

/**
 * Coordinates calculator for column graph.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class CoordinatesCalculatorColumns(data: List<DayQuote>) : AbstractCoordinatesCalculator(data) {
  override fun translateToPanel(side: Double, value: Double, max: Double, min: Double, inverted: Boolean): Double {
    val ratio = (side - 2 * margin) / (min - max)
    val temp = (value - max) * ratio
    return if (inverted) side - temp - margin else temp + margin
  }
}
