package com.quotegraph.controller;

import com.quotegraph.model.DayQuote;
import com.quotegraph.model.SqlConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

/**
 *
 * @author d.peters
 */
public class MysqlLoader extends AbstractDataLoader {

    /**
     * DB configuration data
     */
    private final DbConfig config;

    /**
     * Sql connection
     */
    private final SqlConnection connection;

    /**
     * Default constructor which initializes host, user, user password and the
     * database.
     *
     * @param host The Host
     * @param user User
     * @param password Password of User
     * @param db The required db
     * @param source
     */
    public MysqlLoader(String host, String user, String password, String db, String source) {
        super(source);
        this.failed = false;
        this.config = new DbConfig(host, user, password, db, 3306);
        this.connection = new MysqlConnection(this.config);
        this.load();
    }

    /**
     * This constructor initializes the same data as the default constructor as
     * well as the port of the sql source
     *
     * @param host
     * @param user
     * @param password
     * @param db
     * @param source
     * @param port
     */
    public MysqlLoader(String host, String user, String password, String db, String source, int port) {

        super(source);
        this.failed = false;
        this.config = new DbConfig(host, user, password, db, port);
        this.connection = new MysqlConnection(this.config);
        this.load();
    }

    @Override
    public final void load() {

        double open, high, low, close;
        String query;
        Statement statement;
        ResultSet result;
        SimpleDateFormat format;
        Date date;

        List<DayQuote> list = new ArrayList<>();
        List<Double> ts = new ArrayList<>();

        if (!this.connection.hasError()) {
            try {

                query = "select * from " + source;
                statement = this.connection.getConn().createStatement();
                result = statement.executeQuery(query);
                format = new SimpleDateFormat("dd.MM.yyyy");

                while (result.next()) {

                    date = format.parse(result.getString(2));
                    open = result.getDouble(3);
                    high = result.getDouble(4);
                    low = result.getDouble(5);
                    close = result.getDouble(6);
                    list.add(new DayQuote(date, open, high, low, close));
                    ts.add(TimestampGenerator.dateToTimeStamp(date));

                }

                this.connection.close();
                this.data = list;
                this.minClose = Collections.min(list).getClose();
                this.maxClose = Collections.max(list).getClose();
                this.timeStamps = ts;
                this.minTimeStamp = Collections.min(ts);
                this.maxTimeStamp = Collections.max(ts);

            } catch (SQLException ex) {
                
                System.out.println("Could not execute query");
                this.failed = true;

            } catch (ParseException ex) {

                System.out.println("Internal Error.");

            }
        } else  {
            System.out.println("Failed to access MySql database.");
            this.failed = true;
        }

    }

}
