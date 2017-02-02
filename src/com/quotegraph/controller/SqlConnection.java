package com.quotegraph.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.quotegraph.model.ISqlConnection;

/**
 * Opens a mysql connection. Requires a DbConfig object in order to connect
 *
 * @author Daniel
 */
public class SqlConnection implements ISqlConnection {

    /**
     *
     */
    private String link;

    /**
     * The connection to the sql server
     */
    private Connection conn;

    /**
     * The database configuration details
     */
    private DbConfig config;
    
    /**
     * Type of sql database (postgresql or mysql)
     */
    private String type;

    /**
     * True if there was an error connecting to the database
     */
    private boolean error;

    /**
     *
     * @param config
     * @param type mysql or postgresql
     */
    public SqlConnection(DbConfig config, String type) {

        this.config = config;
        this.type = type;
        this.link = "jdbc:" + type + "://"
                + this.config.getHost()
                + ":" + this.config.getPort()
                + "/" + this.config.getDb();

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
            conn = DriverManager.getConnection(
                    link, this.config.getUser(), this.config.getPassword()
            );
            
        } catch (SQLException ex) {
            this.error = true;
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
