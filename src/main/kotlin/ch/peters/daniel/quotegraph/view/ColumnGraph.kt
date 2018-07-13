package ch.peters.daniel.quotegraph.view

import com.quotegraph.controller.CoordinatesCalculator
import com.quotegraph.model.DayQuote
import com.quotegraph.model.ECoordinates
import java.awt.*

import javax.swing.JPanel

/**
 * Display data as a column graph. TODO: Scaling needs to be improved.
 * Formula for for scaling coordinates: x = (actualValX - MinActualValX) *
 * windowWidth / (MaxActualValX - MinActualValX) y = (actualValY -
 * MinActualValY) * windowHeight / (MaxActualValY - MinActualValY)
 *
 * @author d.peters
 */
class ColumnGraph(data: List<DayQuote>) : JPanel() {
  /**
   * Coordinates calculator.
   */
  private val calculator: CoordinatesCalculator = CoordinatesCalculator(data)

  /**
   * Width of the JPanel.
   */
  private val defaultWidth = 1000

  /**
   * Height of the JPanel.
   */
  private val defaultHeight = 600

  /**
   * Background color of the JPanel.
   */
  private val backgroundColor = Color.yellow

  /**
   * Defines the Margin around the graph.
   */
  private val margin = 40

  private fun paintXAxis(g2: Graphics2D) {
    g2.color = Color.blue
    g2.drawLine(margin, height - margin, width - margin, height - margin)

    (0..19).forEach { i ->
      val marginX = (i + 1) * (width - margin * 2) / (20 - 1) + margin
      val marginY = height - margin
      val marginY1 = marginY - 5
      g2.drawLine(marginX, marginY, marginX, marginY1)
    }
  }

  private fun paintYAxis(g2: Graphics2D) {
    g2.color = Color.blue
    g2.drawLine(margin, height - margin, margin, margin)

    (0..10).forEach { i ->
      val marginY = height - (((i + 1) * (height - margin * 2)) / 10 + margin)
      g2.drawLine(margin, marginY, margin + 5, marginY)
    }
  }

  private fun drawColumns(g2: Graphics2D) {
    val columnWidth = 1
    val ratio = (width - 2 * margin) / 100 - 1

    g2.color = Color.red
    (0..100).forEach { i ->
      val positionX =(i - 1) * ratio
      val columnHeight = calculator . createCoordinate (i, height.toDouble(), ECoordinates.Y1)
      val windowHeight = height - margin
      g2.drawRect(positionX +margin, (windowHeight - columnHeight).toInt(), (columnWidth * ratio), columnHeight.toInt())
    }
  }

  /**
   * Drawing done here.
   *
   * @param g graphics context.
   */
  override fun paintComponent(g: Graphics) {
    val g2 = g as Graphics2D
    super.paintComponent(g2)
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    drawColumns(g2)
    paintXAxis(g2)
    paintYAxis(g2)
  }

  init {
    this.preferredSize = Dimension(defaultWidth, defaultHeight)
    this.background = backgroundColor
  }
}
