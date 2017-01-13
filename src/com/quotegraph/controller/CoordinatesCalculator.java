package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;

/**
 *
 * @author d.peters
 */
public class CoordinatesCalculator {

    private final DataLoader daten;

    public CoordinatesCalculator(DataLoader daten) {
        this.daten = daten;
    }

    /**
     * Abstände der Grafik zum Rand.
     */
    private final int MARGIN = 40;

    /**
     * Beim Koordinatenberechnen wird y berechnet wenn der Parameterstring
     * gleich diesem ist.
     */
    private final String Y_RECHNEN = "y";

    /**
     * Beim Koordinatenberechnen wird y1 berechnet wenn der Parameterstring
     * gleich diesem ist.
     */
    private final String Y1_RECHNEN = "y1";

    /**
     * Beim Koordinatenberechnen wird x berechnet wenn der Parameterstring
     * gleich diesem ist.
     */
    private final String X_RECHNEN = "x";

    /**
     * Beim Koordinatenberechnen wird y1 berechnet wenn der Parameterstring
     * gleich diesem ist.
     */
    private final String X1_RECHNEN = "x1";

    /**
     * Einzelne Koordinaten für die Linien holen
     *
     * @param zaehler Stelle in der ArrayList
     * @param achse
     * @param koordinatTyp unterscheiden zwischen x, x1, y, und y1
     * @return Auf Fenster umgewandelte Koordinatenposition zurückgeben
     */
    public double createCoordinate(int zaehler, double achse, String koordinatTyp) {

        double weltwert = 0, maxwert = 0, minwert = 0;

        /**
         * Unterschiedliche daten Werden geladen in Formel, je nach Koordinat
         * einer Linie
         */
        if (koordinatTyp.equals(Y_RECHNEN) || koordinatTyp.equals(Y1_RECHNEN)) {
            //Minimaler- und maximaler Ywert aus ArrayList holen
            minwert = daten.getMinClose();
            maxwert = daten.getMaxClose();

            if (koordinatTyp.equals(Y_RECHNEN)) {
                //Y Startpunkt
                weltwert = Math.round(daten.getData().get(zaehler - 1).getClose());
            } else {
                //Y Endpunkt
                weltwert = Math.round(daten.getData().get(zaehler).getClose());
            }

        } else if (koordinatTyp.equals(X_RECHNEN) || koordinatTyp.equals(X1_RECHNEN)) {
            //Minimaler und maximaler x Wert
            minwert = daten.getMinTimeStamp();
            maxwert = daten.getMaxTimeStamp();

            if (koordinatTyp.equals(X_RECHNEN)) {
                //X Startpunkt
                weltwert = daten.getTimeStamps().get(zaehler - 1);
            } else {
                //X Endpunkt
                weltwert = daten.getTimeStamps().get(zaehler);
            }

        }
        return translateToPanel(achse, weltwert, minwert, maxwert, koordinatTyp);
    }

    /**
     * Fensterkoordinaten ausrechnen
     *
     * @param panelDimension
     * @param quote
     * @param maxQuote
     * @param minQuote
     * @param koorTyp
     * @return
     */
    public double translateToPanel(double panelDimension, double quote,
            double maxQuote, double minQuote, String koorTyp) {

        double koor;

        double ratio = (panelDimension - 2 * MARGIN) / (minQuote - maxQuote);
        double tempResult = (quote - maxQuote) * ratio;

        //Abfrage, da y Achse umgekehrt werden muss.
        if (koorTyp.equals(Y_RECHNEN) || koorTyp.equals(Y1_RECHNEN)) {
            //Y-Achse umkehren und Abstand zum Rand einsetzen
            koor = Math.round(panelDimension - tempResult - MARGIN);
        } else {
            //Abstand zum Rand einsetzen
            koor = Math.round(tempResult + MARGIN);
        }

        return koor;
    }
}
