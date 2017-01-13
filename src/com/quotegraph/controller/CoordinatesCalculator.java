package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;

/**
 * Scales coordinates to the window to allow for resizing of graph
 *
 * @author d.peters
 */
public class CoordinatesCalculator {

    /**
     * Reference to the DataLoader object
     */
    private final DataLoader loader;

    /**
     * Default constructor.
     *
     * @param loader
     */
    public CoordinatesCalculator(DataLoader loader) {
        this.loader = loader;
    }

    /**
     * Margins around the graph area
     */
    private final int MARGIN = 40;

    /**
     * Calculate y
     */
    private final String CALC_Y = "y";

    /**
     * Calculate y1
     */
    private final String CALC_Y1 = "y1";

    /**
     * Calculate x
     */
    private final String CALC_X = "x";

    /**
     * Calculate x1
     */
    private final String CALC_X1 = "x1";

    /**
     * Get coordinate.
     *
     * @param index current index in the data list
     * @param axis
     * @param coorType cordinate type are x, x1, y, und y1
     * @return scaled coordinate
     */
    public double createCoordinate(int index, double axis, String coorType) {

        double weltwert = 0, maxwert = 0, minwert = 0;

        if (coorType.equals(CALC_Y) || coorType.equals(CALC_Y1)) {

            minwert = loader.getMinClose();
            maxwert = loader.getMaxClose();

            if (coorType.equals(CALC_Y)) {

                weltwert = Math.round(loader.getData().get(index - 1).getClose());

            } else {

                weltwert = Math.round(loader.getData().get(index).getClose());

            }

        } else if (coorType.equals(CALC_X) || coorType.equals(CALC_X1)) {

            minwert = loader.getMinTimeStamp();
            maxwert = loader.getMaxTimeStamp();

            if (coorType.equals(CALC_X)) {

                weltwert = loader.getTimeStamps().get(index - 1);

            } else {

                weltwert = loader.getTimeStamps().get(index);

            }

        }

        return translateToPanel(axis, weltwert, minwert, maxwert, coorType);
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
    public double translateToPanel(double panelDimension, double quote,
            double maxQuote, double minQuote, String coorType) {

        double koor;
        double ratio = (panelDimension - 2 * MARGIN) / (minQuote - maxQuote);
        double tempResult = (quote - maxQuote) * ratio;

        if (coorType.equals(CALC_Y) || coorType.equals(CALC_Y1)) {
            
            // Invert y-axis and add margins
            koor = Math.round(panelDimension - tempResult - MARGIN);
            
        } else {
            
            // Add margins
            koor = Math.round(tempResult + MARGIN);
            
        }

        return koor;
    }
}
