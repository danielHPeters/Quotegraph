package ch.peters.daniel.quotegraph.interfaces

import ch.peters.daniel.quotegraph.controller.DbConfig
import java.sql.Connection

/**
 * Interface used for database abstraction.
 *
 * @author Daniel Peters
 */
interface ISqlConnection {
  var config: DbConfig
  var connection: Connection
  var error: Boolean
  fun connect()
  fun close()
}
