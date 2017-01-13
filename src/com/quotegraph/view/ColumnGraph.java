package com.quotegraph.view;

import com.quotegraph.model.DataLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Display data as a colum graph.
 * TODO: Scaling needs to be improved.
 * 
 * Formula for for scaling coordinates:
 * x = (actualValX - MinActualValX) * windowWidth / (MaxActualValX - MinActualValX); 
 * y = (actualValY - MinActualValY) * windowHeight / (MaxActualValY - MinActualValY);
 *
 * @author d.peters
 */
public class ColumnGraph extends JPanel {

    /**
     * Width of the JPanel
     */
    private final int DEFAULT_WIDTH = 1000;

    /**
     * Height of the JPanel
     */
    private final int DEFAULT_HEIGHT = 600;

    /**
     * Background color of the JPanel
     */
    private final Color BACKGROUND_COLOR = Color.yellow;

    /**
     * Defines the Margin around the graph
     */
    private final int MARGIN = 40;

    /**
     * Test for calculating y value
     */
    private final String CALC_Y = "y";

    /**
     * Test for calculating y1 value
     */
    private final String CALC_Y1 = "y1";

    /**
     * Test for calculating x value
     */
    private final String CALC_X = "x";

    /**
     * Test for calculating x1 value
     */
    private final String CALC_X1 = "x1";

    /**
     * Reference to the DataLoader object.
     */
    private final DataLoader loader;

    /**
     * Setzt das Aussehen und Dimension der Zeichenoberfläche bei Aufruf.
     *
     * @param daten
     */
    public ColumnGraph(DataLoader daten) {

        this.loader = daten;
        initAppearance();

    }

    /**
     *
     */
    private void initAppearance() {

        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setBackground(BACKGROUND_COLOR);

    }

    /**
     * Einzelne Koordinaten für die Linien holen
     *
     * @param zaehler Stelle in der ArrayList
     * @param koordinatTyp unterscheiden zwischen x, x1, y, und y1
     * @return Auf Fenster umgewandelte Koordinatenposition zurückgeben
     */
    private double koordinaten(int zaehler, String koordinatTyp) {

        double weltwert = 0, maxwert = 0, minwert = 0, achse = 0;

        /**
         * Unterschiedliche daten Werden geladen in Formel, je nach Koordinat
         * einer Linie
         */
        if (koordinatTyp.equals(CALC_Y) || koordinatTyp.equals(CALC_Y1)) {
            //Minimaler- und maximaler Ywert aus ArrayList holen
            minwert = loader.getMinClose();
            maxwert = loader.getMaxClose();
            //Fensterhoehe Holen
            achse = getHeight();

            if (koordinatTyp.equals(CALC_Y)) {
                //Y Startpunkt
                weltwert = loader.getData().get(zaehler - 1).getClose();
            } else {
                //Y Endpunkt
                weltwert = loader.getData().get(zaehler).getClose();
            }
        } else if (koordinatTyp.equals(CALC_X) || koordinatTyp.equals(CALC_X1)) {
            //Minimaler und maximaler x Wert
            minwert = loader.getMinTimeStamp();
            maxwert = loader.getMaxTimeStamp();
            //Aktuelle Fensterbreite holen
            achse = getWidth();
            if (koordinatTyp.equals(CALC_X)) {
                //X Startpunkt
                weltwert = loader.getTimeStamps().get(zaehler - 1);
            } else {
                //X Endpunkt
                weltwert = loader.getTimeStamps().get(zaehler);
            }
        }

        return koordinatenBerechnen(achse, weltwert, minwert, maxwert, koordinatTyp);
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
    private double koordinatenBerechnen(double windowDimension, double value, double minVal, double maxVal, String coorType) {

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
        if (coorType.equals(CALC_Y) || coorType.equals(CALC_Y1)) {

            // Y-Achse umkehren und Abstand zum Rand einsetzen
            koor = Math.round(windowDimension - temp - 2 * MARGIN);

        } else {

            // Add Margins
            koor = Math.round(temp + MARGIN);

        }

        return koor;
    }

    /**
     *
     * @param g2
     */
    public void paintXAxis(Graphics2D g2) {

        g2.setColor(Color.blue);
        g2.drawLine(MARGIN, getHeight() - MARGIN, getWidth() - MARGIN, getHeight() - MARGIN);

        for (int i = 0; i < 20 - 1; i++) {

            int xabst = (i + 1) * (getWidth() - MARGIN * 2) / (20 - 1) + MARGIN;
            int xabst1 = xabst;
            int yabst = getHeight() - MARGIN;
            int yabst1 = yabst - 5;
            g2.drawLine(xabst, yabst, xabst1, yabst1);

        }

    }

    /**
     *
     * @param g2
     */
    public void paintYAxis(Graphics2D g2) {

        g2.setColor(Color.blue);
        g2.drawLine(MARGIN, getHeight() - MARGIN, MARGIN, MARGIN);

        for (int i = 0; i < 10; i++) {

            int xabst = MARGIN;
            int xabst1 = 5 + MARGIN;
            int yabst = getHeight() - (((i + 1) * (getHeight() - MARGIN * 2)) / 10 + MARGIN);
            int yabst1 = yabst;
            g2.drawLine(xabst, yabst, xabst1, yabst1);

        }

    }

    /**
     *
     * @param g2
     */
    public void drawColums(Graphics2D g2) {

        int windowHeight, columnWidth = 1;
        double columnHeight, xRatio, xPos;

        xRatio = Math.round((getWidth() - 2 * MARGIN) / 100 - 1);
        g2.setColor(Color.red);

        for (int counter = 1; counter < 100; counter++) {

            xPos = (counter - 1) * xRatio;
            columnHeight = koordinaten(counter, CALC_Y1);
            windowHeight = getHeight() - MARGIN;
            g2.drawRect((int) xPos + MARGIN, windowHeight - (int) columnHeight, (int) (columnWidth * xRatio), (int) columnHeight);

        }

    }

    /**
     * Drawing done here.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawColums(g2);
        paintXAxis(g2);
        paintYAxis(g2);

    }
}
