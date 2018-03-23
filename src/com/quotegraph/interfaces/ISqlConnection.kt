package com.quotegraph.interfaces

import com.quotegraph.controller.DbConfig
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
