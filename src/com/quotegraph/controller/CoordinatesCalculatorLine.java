package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;

/**
 * Scales coordinates to the window to allow for resizing of graph
 *
 * @author d.peters
 */
public class CoordinatesCalculatorLine extends CoordinatesCalculator {
    
    /**
     * Default constructor.
     *
     * @param loader
     */
    public CoordinatesCalculatorLine(DataLoader loader) {
        super(loader);
    }

    /**
     * Translate data items to panel dimensions. Set inverted to true for y axis because 0 starts at top and not bottom.
     *
     * @param panelSide width or height of panel
     * @param quote     data item from quotes
     * @param max       maximum in quotes list
     * @param min       minimum in quotes list
     * @param inverted  boolean to check if axis is inverted
     * @return
     */
    @Override
    public double translateToPanel(double panelSide, double quote, double max, double min, boolean inverted) {

        double ratio = (panelSide - 2 * MARGIN) / (min - max);
        double temp = (quote - max) * ratio;

        return Math.round(inverted ? panelSide - temp - MARGIN : temp + MARGIN);
    }
}
