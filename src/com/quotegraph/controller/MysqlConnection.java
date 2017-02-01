package com.quotegraph.controller;

import com.quotegraph.model.SqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Opens a mysql connection. Requires a DbConfig object in order to connect
 *
 * @author Daniel
 */
public class MysqlConnection implements SqlConnection {

    /**
     *
     */
    private String link;

    /**
     *
     */
    private Connection conn;

    /**
     *
     */
    private DbConfig config;

    /**
     *
     */
    private boolean error;

    /**
     *
     * @param config
     */
    public MysqlConnection(DbConfig config) {

            this.config = config;
            this.link = "jdbc:mysql://" + this.config.getHost() + ":" + this.config.getPort() + "/" + this.config.getDb();

    }

    /**
     *
     * @return
     */
    @Override
    public Connection getConn() {
        return conn;
    }

    @Override
    public boolean hasError() {
        return this.error;
    }

    /**
     *
     */
    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(link, this.config.getUser(), this.config.getPassword());
        } catch (InstantiationException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    @Override
    public void close() {

        try {

            conn.close();

        } catch (SQLException ex) {

            System.out.println("An error occured while closing the connection.");

        }

    }

}
