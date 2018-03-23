package com.quotegraph.interfaces

import com.quotegraph.model.DayQuote

/**
 * Interface for data loading classes
 *
 * @author Daniel Peters
 * @version 1.0
 */
interface IDataLoader {
  var source: String
  var failed: Boolean
  fun load(): List<DayQuote>
}
