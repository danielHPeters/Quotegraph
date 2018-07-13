package ch.peters.daniel.quotegraph.view

import com.quotegraph.controller.CoordinatesCalculator
import com.quotegraph.model.DayQuote
import com.quotegraph.model.ECoordinates
import java.awt.*

import javax.swing.JPanel

/**
 * Displays quote data as a linear graph.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class LineGraph(private val data: List<DayQuote>) : JPanel() {
  /**
   * Reference to coordinates calculator object.
   */
  private val calculator: CoordinatesCalculator = CoordinatesCalculator(data)

  /**
   * Height of the graph panel.
   */
  private val defaultHeight = 600

  /**
   * Width of the graph panel.
   */
  private val defaultWidth = 1000

  /**
   * Background color of the graph panel.
   */
  private val backgroundColor = Color.yellow

  /**
   * Margins around the graph.
   */
  private val margin = 40

  private fun printText(g2: Graphics2D, text: String, x: Double, x1: Double) {
    g2.drawLine(x.toInt(), margin, x1.toInt(), height - margin)
    g2.drawString(text, x.toFloat(), (margin / 2).toFloat())
  }

  /**
   * Draw lines on the graph where major world events happened.
   *
   * @param g2    2D graphics context
   * @param index drawing index
   * @param x     x position
   * @param x1    end x position
   */
  private fun markEvents(g2: Graphics2D, index: Int, x: Double, x1: Double) {
    when (data[index].quoteDate.toString()) {
      "Mon Jan 03 00:00:00 CET 2000" -> printText(g2, "Dotcom", x, x1)
      "Mon Sep 10 00:00:00 CEST 2001" -> printText(g2, "9/11", x, x1)
      "Mon Sep 15 00:00:00 CEST 2008" -> printText(g2, "08 Financial Crisis", x, x1)
      "Tue Apr 27 00:00:00 CEST 2010" -> printText(g2, "Greek Crisis", x, x1)
      "Fri Mar 11 00:00:00 CET 2011" -> printText(g2, "Fukushima", x, x1)
      "Mon Aug 01 00:00:00 CEST 2011" -> printText(g2, "Eurocrisis", x, x1)
      "Fri Jun 12 00:00:00 CEST 2015" -> printText(g2, "Greek Crisis 2.0", x, x1)
    }
  }

  /**
   * Draw lines with quote data.
   *
   * @param g2 graphics context
   */
  private fun drawGraphLine(g2: Graphics2D) {
    val panelX = width.toDouble()
    val panelY = height.toDouble()

    g2.color = Color.red

    for (i in 1..data.size) {
      val y = calculator.createCoordinate(i, panelY, ECoordinates.Y)
      val y1 = calculator.createCoordinate(i, panelY, ECoordinates.Y1)
      val x = calculator.createCoordinate(i, panelX, ECoordinates.X)
      val x1 = calculator.createCoordinate(i, panelX, ECoordinates.X1)

      g2.drawLine(x.toInt(), y.toInt(), x1.toInt(), y1.toInt())
      markEvents(g2, i, x, x1)
    }
  }

  /**
   * Draws the x-axis with time data.
   * TODO: is not at all working as intended and needs to be generalized.
   *
   * @param g2 graphics context
   */
  private fun drawXAxis(g2: Graphics2D) {
    var year = 2000
    g2.drawLine(margin, height - margin, height - margin, height - margin)

    for (i in 10..(17 * 12 + 11)) {
      val distanceX = (i - 11) * (width - margin * 2) / ((16 * 12) + 3) + margin
      val distanceY = height - margin
      val distanceY1 = distanceY - 5

      if (i % 12 == 0) {
        g2.drawString(year.toString(), distanceX, distanceY - 6)
        g2.drawLine(distanceX, distanceY, distanceX, distanceY1)
        year += 1
      }
    }
  }

  /**
   * Draws the y-axis.
   * TODO: is not at all working as intended and needs to be generalized
   *
   * @param g2 graphics context
   */
  private fun drawYAxis(g2: Graphics2D) {
    var marker = 0

    g2.drawLine(margin, height - margin, margin, margin)

    for (i in 0..12) {
      val distanceX = margin
      val distanceX1 = 5 + margin
      val distanceY = height - (((i + 1) * (height - margin * 2)) / 12 + margin)

      marker += 1000
      g2.drawLine(distanceX, distanceY, distanceX1, distanceY)
      g2.drawString("" + marker, distanceX + 6, distanceY)
    }
  }

  /**
   * Paint method override. Executes all the painting methods.
   *
   * @param g graphics context
   */
  override fun paintComponent(g: Graphics) {
    val g2 = g as Graphics2D

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    super.paintComponent(g2)
    drawGraphLine(g2)
    g2.color = Color.blue
    drawXAxis(g2)
    drawYAxis(g2)
  }

  init {
    preferredSize = Dimension(defaultWidth, defaultHeight)
    background = backgroundColor
  }
}
