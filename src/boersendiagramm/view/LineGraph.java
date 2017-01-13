package boersendiagramm.view;

import boersendiagramm.model.CoordinatesCalculator;
import boersendiagramm.model.DataLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Displays quote data as a linear graph
 *
 * @author d.peters
 */
public class LineGraph extends JPanel {

    /**
     * Initial height of the graph panel
     */
    private final int HEIGHT = 600;

    /**
     * Initial width of the graph panel
     */
    private final int WIDTH = 1000;

    /**
     * Backround color of the graph panel
     */
    private final Color BACKGROUNDCOLOR = Color.yellow;

    /**
     * Margins around the graph
     */
    private final int SEITENABSTAND = 40;

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
     *
     */
    private final DataLoader loader;

    /**
     *
     */
    private final CoordinatesCalculator calculator;

    /**
     * Default constructor which initializes the data and appearance of the
     * panel
     *
     * @param daten
     * @param calculator
     */
    public LineGraph(DataLoader daten, CoordinatesCalculator calculator) {
        this.calculator = calculator;
        this.loader = daten;
        initAppearance();
    }

    /**
     * Initialize the appearance of this panel
     */
    private void initAppearance() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(BACKGROUNDCOLOR);
    }

    /**
     * Draw lines on the graph where major world events happened
     *
     * @param g2 Graphics object
     * @param index
     * @param x
     * @param x1
     */
    public void markEvents(Graphics2D g2, int index, double x, double x1) {

        switch (loader.getData().get(index).getQuoteDate().toString()) {

            case "Mon Jan 03 00:00:00 CET 2000":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("Dotcom", (int) x, SEITENABSTAND / 2);
                break;

            case "Mon Sep 10 00:00:00 CEST 2001":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("9/11", (int) x, SEITENABSTAND / 2);
                break;

            case "Mon Sep 15 00:00:00 CEST 2008":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("Finanzkrise", (int) x, SEITENABSTAND / 2);
                break;

            case "Tue Apr 27 00:00:00 CEST 2010":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("Griechenlandkrise", (int) x, SEITENABSTAND / 4);
                break;

            case "Fri Mar 11 00:00:00 CET 2011":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("Fukushima", (int) x, SEITENABSTAND / 2);
                break;

            case "Mon Aug 01 00:00:00 CEST 2011":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("Eurokrise", (int) x, SEITENABSTAND);
                break;

            case "Fri Jun 12 00:00:00 CEST 2015":
                g2.drawLine((int) x, SEITENABSTAND, (int) x1, getHeight() - SEITENABSTAND);
                g2.drawString("Griechenlandkrise 2.0", (int) x, SEITENABSTAND / 2);
                break;

        }

    }

    /**
     * Draw lines whith quote data
     *
     * @param g2 Graphics2D Zeichenobjekt
     */
    public void drawGraphLine(Graphics2D g2) {

        double y, y1, x, x1, panelX, panelY;

        panelX = this.getWidth();
        panelY = this.getHeight();

        g2.setColor(Color.red);

        for (int i = 1; i < loader.getData().size(); i++) {

            y = calculator.createCoordinate(i, panelY, Y_RECHNEN);
            y1 = calculator.createCoordinate(i, panelY, Y1_RECHNEN);
            x = calculator.createCoordinate(i, panelX, X_RECHNEN);
            x1 = calculator.createCoordinate(i, panelX, X1_RECHNEN);

            g2.drawLine((int) x, (int) y, (int) x1, (int) y1);

            markEvents(g2, i, x, x1);

        }

    }

    /**
     * Draws the x-axis with time data
     *
     * @param g2 Graphics2D Zeichenobjekt
     */
    public void drawXAxis(Graphics2D g2) {
        //X-Achse zeichnen
        g2.drawLine(SEITENABSTAND, getHeight() - SEITENABSTAND, getWidth() - SEITENABSTAND, getHeight() - SEITENABSTAND);
        //Jahr zut Beschriftung der Achse.
        int jahr = 2000;
        //Abstandmarkierungen auf X-Achse
        for (int i = 10; i < (17 * 12) + 11; i++) {
            int xabst = (i - 11) * (getWidth() - SEITENABSTAND * 2) / ((16 * 12) + 3) + SEITENABSTAND;
            int xabst1 = xabst;
            int yabst = getHeight() - SEITENABSTAND;
            int yabst1 = yabst - 5;

            /**
             * Da die Daten nicht in jeder Datei beim Januar anfangen, geht die
             * For-Schleife durch monate hindurch und Gibt bei jedem Jahr eine
             * Markierung mit der Jahreszahl aus. Bemerkung: Ist nicht final und
             * muss noch überarbeitet werden.
             */
            if (i % 12 == 0) {
                g2.drawString("" + jahr, xabst, yabst - 6);
                g2.drawLine(xabst, yabst, xabst1, yabst1);
                jahr += 1;
            }

        }
    }

    /**
     * Draws the y-axis
     *
     * @param g2 Graphics2D graphics object
     */
    public void drawYAxis(Graphics2D g2) {
        //Y-Achse zeichnen
        g2.drawLine(SEITENABSTAND, getHeight() - SEITENABSTAND, SEITENABSTAND, SEITENABSTAND);
        int beschriftung = 0;
        //Abstandmarkierungen auf Y-Achse
        for (int i = 0; i < 12; i++) {
            beschriftung += 1000;
            int xabst = SEITENABSTAND;
            int xabst1 = 5 + SEITENABSTAND;
            int yabst = getHeight() - (((i + 1) * (getHeight() - SEITENABSTAND * 2)) / 12 + SEITENABSTAND);
            int yabst1 = yabst;
            g2.drawLine(xabst, yabst, xabst1, yabst1);
            g2.drawString("" + beschriftung, xabst + 6, yabst);
        }
    }

    /**
     * Paint method override. Excecutes all the painting methods
     *
     * @param g Graphics graphics object
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);

        drawGraphLine(g2);

        g2.setColor(Color.blue);
        drawXAxis(g2);
        drawYAxis(g2);

    }

}
