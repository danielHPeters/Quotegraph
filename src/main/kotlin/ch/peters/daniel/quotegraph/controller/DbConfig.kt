package ch.peters.daniel.quotegraph.controller

/**
 * Database configuration information container class.
 *
 * @author Daniel Peters
 * @version 2.0
 */
class DbConfig(
  var type: String,
  var host: String,
  var user: String,
  var password: String,
  var database: String,
  var port: Int
)
