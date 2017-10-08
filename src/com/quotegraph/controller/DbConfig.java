package com.quotegraph.controller;

/**
 * Database configuration information container class.
 *
 * @author Daniel Peters
 * @version 1.2
 */
public class DbConfig {
  private String type;
  private String host;
  private String db;
  private String user;
  private String password;
  private int port;

  /**
   * This constructor also initializes the Port.
   *
   * @param type     db type
   * @param host     db host
   * @param user     db user
   * @param password user password
   * @param db       db name
   * @param port     db port
   */
  public DbConfig(String type, String host, String user, String password, String db, int port) {
    this.type = type;
    this.host = host;
    this.user = user;
    this.password = password;
    this.db = db;
    this.port = port;
  }

  public String getType() {
    return type;
  }

  public String getHost() {
    return host;
  }

  public String getDb() {
    return db;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public int getPort() {
    return port;
  }
}
