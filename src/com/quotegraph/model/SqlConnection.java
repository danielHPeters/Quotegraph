package com.quotegraph.model;

import java.sql.Connection;

/**
 * Interface used for database abstraction
 * 
 * @author Daniel
 */
public interface SqlConnection {
    
    /**
     * 
     */
    public void connect();
    
    /**
     * 
     */
    public void close();
    
    /**
     * 
     * @return 
     */
    public Connection getConn();
    
    /**
     * 
     * @return 
     */
    public boolean hasError();
    
}
