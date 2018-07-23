package ch.peters.daniel.quotegraph

import java.awt.Dimension

/**
 * App configuration.
 *
 * @author Daniel Peters
 * @version 2.0
 */
class Config {
  private val panelDimension: Dimension = Dimension(1000, 600)
  private val margins: Map<String, Double> = mapOf(
      "top" to 10.0,
      "bottom" to 10.0,
      "left" to 10.0,
      "right" to 10.0
  )
}
