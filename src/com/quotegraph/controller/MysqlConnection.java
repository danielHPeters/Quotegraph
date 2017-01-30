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
     * @param config
     */
    public MysqlConnection(DbConfig config) {

        this.config = config;
        this.link = "jdbc:mysql://" + this.config.getHost() + ":" + this.config.getPort() + "/" + this.config.getDb();

        try {

            conn = DriverManager.getConnection(link, this.config.getUser(), this.config.getPassword());

        } catch (SQLException ex) {
            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Connection getConn() {
        return conn;
    }

    /**
     * 
     */
    @Override
    public void connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     */
    @Override
    public void close() {

        try {

            conn.close();

        } catch (SQLException ex) {

            Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
