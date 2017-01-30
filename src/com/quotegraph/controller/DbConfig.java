package com.quotegraph.controller;

/**
 *
 * @author Daniel
 */
public class DbConfig {
    
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
    private String port;
    
    /**
     * Default constructor
     * 
     * @param host
     * @param user
     * @param password
     * @param db 
     */
    public DbConfig(String host, String user, String password, String db){
        
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        
    }
    
    /**
     * Thisconstructor also initializes the Port
     * 
     * @param host
     * @param user
     * @param password
     * @param db
     * @param port 
     */
    public DbConfig(String host, String user, String password, String db, String port){
        
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
        this.port = port;
        
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
    public String getPort() {
        return port;
    }

    /**
     * 
     * @param port 
     */
    public void setPort(String port) {
        this.port = port;
    }
    
    
}
