package com.quotegraph.controller

import com.quotegraph.interfaces.IDataLoader
import com.quotegraph.model.DayQuote

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList

/**
 * Loads the quotes data from a file.
 *
 * @author Daniel Peters
 * @version 1.2
 */
class FileLoader(override var source: String) : IDataLoader {
  override var failed: Boolean = false
  var dateFormat: String = "dd.MM.yyyy"

  /**
   * Load the data from the file and add each line to the list.
   */
  override fun load(): List<DayQuote> {
    val list = ArrayList<DayQuote>()

    try {
      val br = BufferedReader(FileReader(source + ".csv"))
      val format = SimpleDateFormat(dateFormat)

      br.lines().forEach({ line: String ->
        val token = line.split("")
        val dat = format.parse(token[0])
        val start = token[1].toDouble()
        val high = token[2].toDouble()
        val low = token[3].toDouble()
        val close = token[4].toDouble()

        list.add(DayQuote(dat, start, high, low, close))
      })
    } catch (ex: IOException) {
      System.out.println("Failed to open file: $source.csv")
      this.failed = true
    } catch (ex: ParseException) {
      System.out.println("Failed to parse date. " + ex)
    }
    return list
  }
}
