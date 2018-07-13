package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.interfaces.IDataLoader
import ch.peters.daniel.quotegraph.model.DayQuote

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList

/**
 * Loads the quotes data from a file.
 *
 * @author Daniel Peters
 * @version 1.2
 */
class FileLoader(override var source: String) : IDataLoader {
  override var failed: Boolean = false
  private var datePatter: String = "dd.MM.uuuu"

  /**
   * Load the data from the file and add each line to the list.
   */
  override fun load(): List<DayQuote> {
    val list = ArrayList<DayQuote>()

    try {
      val url = javaClass.classLoader.getResource("$source.csv")
      val br = BufferedReader(InputStreamReader(url.openStream()))
      br.lines().forEach { line: String ->
        System.out.println(line)
        val token = line.split(";")
        val dat = LocalDate.parse(token[0], DateTimeFormatter.ofPattern(datePatter)).atStartOfDay()
        val start = token[1].toDouble()
        val high = token[2].toDouble()
        val low = token[3].toDouble()
        val close = token[4].toDouble()

        list.add(DayQuote(dat, start, high, low, close))
      }
    } catch (ex: IOException) {
      System.out.println("Failed to open file: $source.csv")
      this.failed = true
    } catch (ex: ParseException) {
      System.out.println("Failed to parse date. $ex")
    }
    return list
  }
}
