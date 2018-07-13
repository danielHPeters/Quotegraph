package ch.peters.daniel.quotegraph.model

import java.util.Date

/**
 * Represents data from a daily quote as an object.
 *
 * @author Daniel Peters
 */
class DayQuote(
    val quoteDate: Date,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double) : Comparable<DayQuote> {

  override fun compareTo(o: DayQuote): Int {
    return this.close.compareTo(o.close)
  }
}
