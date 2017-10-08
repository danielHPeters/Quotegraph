package com.quotegraph.model;

import java.sql.Connection;

/**
 * Interface used for database abstraction.
 *
 * @author Daniel Peters
 */
public interface ISqlConnection {
  void connect();

  void close();

  Connection getConn();

  boolean hasError();
}
