package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.DayQuote;
import java.util.List;

/**
 *
 * @author d.peters
 */
abstract class AbstractDataLoader implements DataLoader{
    
    /**
     *
     */
    protected final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";

    /**
     * The file containing quotes to be opened
     */
    protected String source;

    /**
     *
     */
    protected List<DayQuote> data;

    /**
     *
     */
    protected List<Double> timeStamps;

    /**
     *
     */
    protected double minClose;

    /**
     *
     */
    protected double maxClose;

    /**
     *
     */
    protected double minTimeStamp;

    /**
     *
     */
    protected double maxTimeStamp;

    /**
     *
     */
    protected boolean failed;

    /**
     * Default constructor. Sets the fileName to default
     * @param source
     */
    public AbstractDataLoader(String source) {
        this.failed = false;
        this.source = source;
    }

    /**
     * Get the fileName
     *
     * @param source
     */
    @Override
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Get the data List
     *
     * @return
     */
    @Override
    public List<DayQuote> getData() {
        return this.data;
    }

    /**
     * Get the minimum close
     *
     * @return
     */
    @Override
    public double getMinClose() {
        return this.minClose;
    }

    /**
     * Get the maximum close
     *
     * @return
     */
    @Override
    public double getMaxClose() {
        return this.maxClose;
    }

    /**
     * Get the timestamp list
     *
     * @return
     */
    @Override
    public List<Double> getTimeStamps() {
        return this.timeStamps;
    }

    /**
     * Get the lowest value timestamp
     *
     * @return
     */
    @Override
    public double getMinTimeStamp() {
        return this.minTimeStamp;
    }

    /**
     * Get the highest value timestamp
     *
     * @return
     */
    @Override
    public double getMaxTimeStamp() {
        return this.maxTimeStamp;
    }

    /**
     * Boolean flag for loading or source error
     *
     * @return
     */
    @Override
    public boolean hasFailed() {
        return this.failed;
    }

    @Override
    abstract public void load();
    
}
