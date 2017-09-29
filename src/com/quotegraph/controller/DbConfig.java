package com.quotegraph.controller;

/**
 *
 * @author Daniel Peters
 * @version 1.2
 */
public class DbConfig {

    /**
     * Type of database
     */
    private String type;

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
     * Default constructor
     */
    private int port;
    
    /**
     * Default constructor
     *
     * @param type
     * @param host
     * @param user
     * @param password
     * @param db 
     */
    public DbConfig(String type, String host, String user, String password, String db){

        this.type = type;
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        
    }
    
    /**
     * This constructor also initializes the Port
     *
     * @param type
     * @param host
     * @param user
     * @param password
     * @param db
     * @param port 
     */
    public DbConfig(String type, String host, String user, String password, String db, int port){

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

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return 
     */
    public String getHost() {
        return host;
    }

    /**
     * 
     * @param host 
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 
     * @return 
     */
    public String getDb() {
        return db;
    }

    /**
     * 
     * @param db 
     */
    public void setDb(String db) {
        this.db = db;
    }

    /**
     * 
     * @return 
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user 
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return 
     */
    public int getPort() {
        return port;
    }

    /**
     * 
     * @param port 
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    
}
