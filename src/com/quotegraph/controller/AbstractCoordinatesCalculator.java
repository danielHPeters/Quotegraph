package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;

/**
 *
 * @author d.peters
 */
public abstract class AbstractCoordinatesCalculator {
    
    /**
     * Defines the Margin around the graph
     */
    protected final int MARGIN = 40;

    /**
     * Reference to the DataLoader object
     */
    protected final DataLoader loader;

    /**
     * Default constructor.
     *
     * @param loader
     */
    public AbstractCoordinatesCalculator(DataLoader loader) {
        this.loader = loader;
    }

    /**
     * Einzelne Koordinaten für die Linien holen
     *
     * @param index Stelle in der ArrayList
     * @param axis
     * @param coorType unterscheiden zwischen x, x1, y, und y1
     * @return Auf Fenster umgewandelte Koordinatenposition zurückgeben
     */
    public double createCoordinate(int index, double axis, ECoordinates coorType) {

        double weltwert = 0, maxVal = 0, minVal = 0;

        if (coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1)) {
            minVal = loader.getMinClose();
            maxVal = loader.getMaxClose();

            if (coorType.equals(ECoordinates.Y)) {

                weltwert = loader.getData().get(index - 1).getClose();

            } else {

                weltwert = loader.getData().get(index).getClose();

            }
        } else if (coorType.equals(ECoordinates.X) || coorType.equals(ECoordinates.X1)) {

            minVal = loader.getMinTimeStamp();
            maxVal = loader.getMaxTimeStamp();

            if (coorType.equals(ECoordinates.X)) {

                weltwert = loader.getTimeStamps().get(index - 1);
            } else {

                weltwert = loader.getTimeStamps().get(index);
            }
        }

        return translateToPanel(axis, weltwert, minVal, maxVal, coorType);
    }
    
    public abstract double translateToPanel(double panelDimension, double quote,
            double maxQuote, double minQuote, ECoordinates coorType);
    
}
