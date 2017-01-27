package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;

/**
 * Scales coordinates to the window to allow for resizing of graph
 *
 * @author d.peters
 */
public class CoordinatesCalculatorLine extends AbstractCoordinatesCalculator{
    
    /**
     * Default constructor.
     *
     * @param loader
     */
    public CoordinatesCalculatorLine(DataLoader loader) {
        super(loader);
    }

    /**
     * Calculate window coordinates.
     *
     * @param panelDimension
     * @param quote
     * @param maxQuote
     * @param minQuote
     * @param coorType
     * @return
     */
    @Override
    public double translateToPanel(double panelDimension, double quote,
            double maxQuote, double minQuote, ECoordinates coorType) {

        double koor;
        double ratio = (panelDimension - 2 * this.MARGIN) / (minQuote - maxQuote);
        double tempResult = (quote - maxQuote) * ratio;

        if (coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1)) {
            
            // Invert y-axis and add margins
            koor = Math.round(panelDimension - tempResult - this.MARGIN);
            
        } else {
            
            // Add margins
            koor = Math.round(tempResult + this.MARGIN);
            
        }

        return koor;
    }
}
