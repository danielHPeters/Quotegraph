package ch.peters.daniel.quotegraph.view

import ch.peters.daniel.quotegraph.model.DayQuote

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

/**
 * Generates a Candlestick Graph.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class CandleStickGraph(val data: List<DayQuote>) : JPanel() {

  /**
   * Default width of the graph.
   */
  private val defaultWidth: Int = 1000

  /**
   * Default height of the graph.
   */
  private val defaultHeight: Int = 600

  /**
   * Background color of the Graph.
   */
  private val backgroundColor: Color = Color.black

  /**
   * Initializes the dimension and the background color of this JPanel.
   */
  private fun initAppearance() {
    preferredSize = Dimension(defaultWidth, defaultHeight)
    background = backgroundColor
  }

  /**
   * Colors the candlestick by comparing the open and close values.
   *
   * @param open  current open value
   * @param close current close value
   * @return color showing a drop or rise of the value
   */
  private fun candleColor(open: Double, close: Double): Color {
    return when {
      close > open -> Color.green
      close < open -> Color.red
      else -> Color.white
    }
  }

  /**
   * Calculate the top shadow of the candlestick.
   *
   * @param high        current high
   * @param openOrClose current open or close
   * @return high - openOrClose the difference between the two submitted values
   */
  private fun getTopLineLength(high: Double, openOrClose: Double): Double {
    return high - openOrClose
  }

  /**
   * Calculate the bottom shadow of the candlestick.
   *
   * @param openOrClose Aktueller Start oder Schlusswert
   * @param low         Aktueller Niedrigstwert
   * @return openOrClose - low
   */
  private fun getLowLineLength(openOrClose: Double, low: Double): Double {
    return openOrClose - low
  }

  /**
   * Calculate the height of the candle.
   *
   * @param openOrClose  current open or close
   * @param openOrClose1 current open or close
   * @return rectangle height.
   */
  private fun getRectHeight(openOrClose: Double, openOrClose1: Double): Double {
    return openOrClose - openOrClose1
  }

  /**
   * Drawing of a candlestick.
   *
   * @param g2              Graphics2D drawing context
   * @param open            current open
   * @param close           current close
   * @param high            current high
   * @param low             current low
   * @param leftRightMargin margin on x dimension
   */
  private fun drawCandle(
      g2: Graphics2D, open: Double, close: Double, high: Double, low: Double, leftRightMargin: Int) {
    val lineTopStartY = 50
    val rectHeight: Double
    val topLineLength: Double
    val lowerLineLength: Double
    val scaleFactor = 10

    if (close > open) {
      topLineLength = getTopLineLength(high, close)
      lowerLineLength = getLowLineLength(open, low)
      rectHeight = getRectHeight(close, open) * scaleFactor
    } else {
      topLineLength = getTopLineLength(high, open)
      lowerLineLength = getLowLineLength(close, low)
      rectHeight = getRectHeight(open, close) * scaleFactor
    }
    val lineTopEndY = lineTopStartY + topLineLength * scaleFactor
    val lineBottomStartY = lineTopEndY + rectHeight
    val lineBottomEndY = lineBottomStartY + lowerLineLength * scaleFactor
    val rectWidth = 50

    g2.color = candleColor(open, close)
    g2.drawLine(leftRightMargin, lineTopStartY, leftRightMargin, lineTopEndY.toInt())
    g2.fillRect(leftRightMargin - rectWidth / 2, lineTopEndY.toInt(), rectWidth, rectHeight.toInt())
    g2.drawLine(leftRightMargin, lineBottomStartY.toInt(), leftRightMargin, lineBottomEndY.toInt())
  }

  /**
   * Draw multiple candlesticks in loop.
   *
   * @param g2 graphics context
   */
  private fun candleDrawingLoop(g2: Graphics2D) {
    var leftRightMargin = 0

    for (i in 1400..1410) {
      val open = data[i].open
      val close = data[i].close
      val high = data[i].high
      val low = data[i].low
      leftRightMargin += 200

      drawCandle(g2, open, close, high, low, leftRightMargin)
    }
  }

  /**
   * Main drawing function draws all items on the JPanel area.
   *
   * @param g Graphics Objekt zum Zeichnen
   */
  override fun paintComponent(g: Graphics) {
    val g2 = g as Graphics2D
    super.paintComponent(g2)
    candleDrawingLoop(g2)
  }

  init {
    initAppearance()
  }
}
