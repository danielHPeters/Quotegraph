package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;

/**
 * @author d.peters
 */
public class CoordinatesCalculatorColumns extends CoordinatesCalculator {

    /**
     * Default constructor.
     *
     * @param loader
     */
    public CoordinatesCalculatorColumns(DataLoader loader) {

        super(loader);

    }

    @Override
    public double translateToPanel(double panelSide, double value, double max, double min, boolean inverted) {

        double ratio = (panelSide - 2 * MARGIN) / (min - max);
        double temp = (value - max) * ratio;

        return Math.round(inverted ? panelSide - temp - MARGIN : temp + MARGIN);
    }

}
