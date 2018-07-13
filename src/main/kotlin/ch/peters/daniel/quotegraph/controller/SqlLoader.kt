package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.interfaces.IDataLoader
import ch.peters.daniel.quotegraph.model.DayQuote
import ch.peters.daniel.quotegraph.interfaces.ISqlConnection

import java.sql.SQLException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList

/**
 * Data loader that gets data from database.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class SqlLoader(override var source: String, private var config: DbConfig) : IDataLoader {
  override var failed: Boolean = false
  private var connection: ISqlConnection = SqlConnection(this.config)

  override fun load(): List<DayQuote> {
    val list = ArrayList<DayQuote>()

    try {
      connection.connect()
      if (!connection.error) {
        val query = "select * from " + source
        val statement = connection.connection.createStatement()
        val result = statement.executeQuery(query)
        val format = SimpleDateFormat("dd.MM.yyyy")

        while (result.next()) {
          val date = format.parse(result.getString(2))
          val open = result.getDouble(3)
          val high = result.getDouble(4)
          val low = result.getDouble(5)
          val close = result.getDouble(6)

          list.add(DayQuote(date, open, high, low, close))
        }
        connection.close()
      } else {
        System.out.println("Failed to access MySql database.")
        failed = true
      }
    } catch (ex: SQLException) {
      System.out.println("Could not execute query")
      failed = true
    } catch (ex: ParseException) {
      System.out.println("Internal Error.")
      failed = true
    }
    return list
  }
}
