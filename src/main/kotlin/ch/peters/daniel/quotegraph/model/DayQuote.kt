package ch.peters.daniel.quotegraph.model

import java.time.LocalDateTime

/**
 * Represents data from a daily quote as an object.
 *
 * @author Daniel Peters
 * @version 2.0
 */
class DayQuote(
    val quoteDate: LocalDateTime,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double
) : Comparable<DayQuote> {
  override fun compareTo(other: DayQuote): Int {
    return this.close.compareTo(other.close)
  }
}
