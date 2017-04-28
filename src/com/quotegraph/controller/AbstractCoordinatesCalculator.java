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

        double weltwert, maxVal, minVal;

        if (coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1)) {
            minVal = loader.getMinClose();
            maxVal = loader.getMaxClose();

            weltwert = coorType.equals(ECoordinates.Y)
                    ? loader.getData().get(index - 1).getClose()
                    : loader.getData().get(index).getClose();

        } else {

            minVal = loader.getMinTimeStamp();
            maxVal = loader.getMaxTimeStamp();

            weltwert = coorType.equals(ECoordinates.X)
                    ? loader.getTimeStamps().get(index - 1)
                    : loader.getTimeStamps().get(index);
        }

        return translateToPanel(axis, weltwert, minVal, maxVal, coorType);
    }

    public abstract double translateToPanel(double panelDimension, double quote,
            double maxQuote, double minQuote, ECoordinates coorType);

}
