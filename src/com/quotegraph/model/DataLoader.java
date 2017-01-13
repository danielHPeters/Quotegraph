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
    
    public double getMinClose();
    
    public double getMaxClose();
    
    public List<Double> getTimeStamps();
    
    public double getMinTimeStamp();
    
    public double getMaxTimeStamp();
    
    public boolean hasFailed();

}
