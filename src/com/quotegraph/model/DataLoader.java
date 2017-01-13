package com.quotegraph.model;

import java.util.List;

/**
 *
 * @author d.peters
 */
public interface DataLoader {

    /**
     * Load data using default source
     *
     */
    public void load();

    /**
     * Set the Source of the data
     *
     * @param source
     */
    public void setSource(String source);
    
    /**
     * 
     * @return 
     */
    public List<DayQuote> getData();
    
    /**
     * 
     * @return 
     */
    public double getMinClose();
    
    /**
     * 
     * @return 
     */
    public double getMaxClose();
    
    /**
     * 
     * @return 
     */
    public List<Double> getTimeStamps();
    
    /**
     * 
     * @return 
     */
    public double getMinTimeStamp();
    
    /**
     * 
     * @return 
     */
    public double getMaxTimeStamp();
    
    /**
     * 
     * @return 
     */
    public boolean hasFailed();

}
