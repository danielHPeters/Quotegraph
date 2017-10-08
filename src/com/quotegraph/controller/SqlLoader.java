package com.quotegraph.controller;

import com.quotegraph.model.DayQuote;
import com.quotegraph.model.ISqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Data loader that gets data from db.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class SqlLoader extends AbstractDataLoader {

  /**
   * DB configuration data.
   */
  private final DbConfig config;

  /**
   * Sql connection.
   */
  private final ISqlConnection connection;

  /**
   * Default constructor which initializes host, user, user password and the
   * database.
   *
   * @param host     The Host
   * @param user     User
   * @param password Password of User
   * @param db       The required db
   * @param source   the db source.
   */
  public SqlLoader(String host, String user, String password, String db, String source) {
    super(source);
    this.failed = false;
    this.config = new DbConfig("postgresql", host, user, password, db, 5432/*3306*/);
    this.connection = new SqlConnection(this.config);
    this.load();
  }

  @Override
  public final void load() {
    String query;
    Statement statement;
    ResultSet result;
    SimpleDateFormat format;

    List<DayQuote> list = new ArrayList<>();
    List<Double> ts = new ArrayList<>();

    try {
      connection.connect();
      if (!connection.hasError()) {
        query = "select * from " + source;
        statement = connection.getConn().createStatement();
        result = statement.executeQuery(query);
        format = new SimpleDateFormat("dd.MM.yyyy");

        while (result.next()) {
          Date date = format.parse(result.getString(2));
          double open = result.getDouble(3);
          double high = result.getDouble(4);
          double low = result.getDouble(5);
          double close = result.getDouble(6);

          list.add(new DayQuote(date, open, high, low, close));
          ts.add(TimestampGenerator.dateToTimeStamp(date));
        }
        connection.close();
        data = list;
        minClose = Collections.min(list).getClose();
        maxClose = Collections.max(list).getClose();
        timeStamps = ts;
        minTimeStamp = Collections.min(ts);
        maxTimeStamp = Collections.max(ts);
      } else {
        System.out.println("Failed to access MySql database.");
        failed = true;
      }
    } catch (SQLException ex) {
      System.out.println("Could not execute query");
      failed = true;
    } catch (ParseException ex) {
      System.out.println("Internal Error.");
      failed = true;
    }
  }
}
