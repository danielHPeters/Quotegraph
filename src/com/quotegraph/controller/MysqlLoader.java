package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.DayQuote;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.peters
 */
public class MysqlLoader implements DataLoader {

    /**
     * Host name
     */
    private String host;

    /**
     * The requested db
     */
    private String db;

    /**
     * The user used for opening a connection
     */
    private String user;

    /**
     * The password of the user
     */
    private String password;

    /**
     * Port of the connection
     */
    private String port;

    /**
     * The table to be selected
     */
    private String table;

    private List<DayQuote> data;

    private List<Double> timeStamps;

    private double minClose;

    private double maxClose;

    private double minTimeStamp;

    private double maxTimeStamp;

    private boolean failed;

    /**
     * Default constructor which initializes host, user, user password and the
     * database.
     *
     * @param host The Host
     * @param user User
     * @param password Password of User
     * @param db The required db
     */
    public MysqlLoader(String host, String user, String password, String db) {
        this.failed = false;
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        this.port = "3306";
        this.table = "blackrock";
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
     * @param port
     */
    public MysqlLoader(String host, String user, String password, String db, String port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        this.port = port;
        this.table = "blackrock";
        this.load();
    }

    @Override
    public final void load() {

        String link;
        Connection conn;
        String query;
        Statement statement;
        ResultSet result;

        SimpleDateFormat format;

        List<DayQuote> list;
        List<Double> ts;
        Date date;
        double open, high, low, close;

        list = new ArrayList<>();
        ts = new ArrayList<>();

        try {

            link = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(link, user, password);
            query = "select * from " + table;
            statement = conn.createStatement();
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

            conn.close();
            this.data = list;
            this.minClose = Collections.min(list).getClose();
            this.maxClose = Collections.max(list).getClose();
            this.timeStamps = ts;
            this.minTimeStamp = Collections.min(ts);
            this.maxTimeStamp = Collections.max(ts);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("Failed to access MySql Database.");
            this.failed = true;
        } catch (ParseException ex) {
            System.out.println("Internal Error.");
        }

    }

    @Override
    public void setSource(String source) {
        this.table = source;
    }

    @Override
    public List<DayQuote> getData() {
        return this.data;
    }

    @Override
    public double getMinClose() {
        return this.minClose;
    }

    @Override
    public double getMaxClose() {
        return this.maxClose;
    }

    @Override
    public List<Double> getTimeStamps() {
        return this.timeStamps;
    }

    @Override
    public double getMinTimeStamp() {
        return this.minTimeStamp;
    }

    @Override
    public double getMaxTimeStamp() {
        return this.maxTimeStamp;
    }

    @Override
    public boolean hasFailed() {
        return this.failed;
    }

}
