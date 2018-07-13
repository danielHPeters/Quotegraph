package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.interfaces.ISqlConnection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Opens a mysql connection. Requires a DbConfig object in order to connect.
 *
 * @author Daniel Peters
 */
class SqlConnection(override var config: DbConfig) : ISqlConnection {
  override lateinit var connection: Connection
  override var error: Boolean = false
  private var link: String = ""

  private fun setLink() {
    link = "jdbc:" + config.type + "://" + config.host + ":" + config.port + "/" + config.database
  }

  override fun connect() {
    try {
      setLink()
      connection = DriverManager.getConnection(link, config.user, config.password)
    } catch (ex: SQLException) {
      error = true
    }
  }

  override fun close() {
    try {
      connection.close()
    } catch (ex: SQLException) {
      System.out.println("An error occurred while closing the connection.")
    }
  }
}
