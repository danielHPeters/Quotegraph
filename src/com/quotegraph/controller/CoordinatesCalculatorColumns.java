package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;

/**
 *
 * @author d.peters
 */
public class CoordinatesCalculatorColumns extends AbstractCoordinatesCalculator {

    /**
     * Default constructor.
     *
     * @param loader
     */
    public CoordinatesCalculatorColumns(DataLoader loader) {

        super(loader);

    }

    /**
     * Fensterkoordinaten ausrechnen
     *
     * @param windowDimension
     * @param value
     * @param minVal
     * @param maxVal
     * @param coorType
     * @return
     */
    @Override
    public double translateToPanel(double windowDimension, double value,
            double minVal, double maxVal, ECoordinates coorType) {

        double koor;

        /**
         * Verhältnis von Fensterwert zu Originalwer Abstände werden zu allen
         * Seiten mit einberechnet
         */
        double ratio = (windowDimension - 2 * MARGIN) / (maxVal - minVal);

        /**
         * Da die Y Koordinaten im JPanel umgekehrt werden müssen, wird das
         * Resultat der Formel zwischengespeichert
         */
        double temp = (value - minVal) * ratio;

        // Abfrage, da y Achse umgekehrt werden muss.
        if (coorType.equals(ECoordinates.Y) || coorType.equals(ECoordinates.Y1)) {

            // Y-Achse umkehren und Abstand zum Rand einsetzen
            koor = Math.round(windowDimension - temp - 2 * MARGIN);

        } else {

            // Add Margins
            koor = Math.round(temp + MARGIN);

        }

        return koor;
    }

}
