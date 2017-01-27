package com.quotegraph.view;

import com.quotegraph.controller.CoordinatesCalculatorLine;
import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;
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
     * Height of the graph panel
     */
    private final int DEFAULT_HEIGHT = 600;

    /**
     * Width of the graph panel
     */
    private final int DEFAULT_WIDTH = 1000;

    /**
     * Backround color of the graph panel
     */
    private final Color BACKGROUND_COLOR = Color.yellow;

    /**
     * Margins around the graph
     */
    private final int MARGIN = 40;

    /**
     * Reference to the DataLoader object
     */
    private final DataLoader loader;

    /**
     *
     */
    private final CoordinatesCalculatorLine calculator;

    /**
     * Default constructor which initializes the data and appearance of the
     * panel
     *
     * @param loader
     */
    public LineGraph(DataLoader loader) {

        this.calculator = new CoordinatesCalculatorLine(loader);
        this.loader = loader;
        initAppearance();

    }

    /**
     * Initialize the appearance of this panel
     */
    private void initAppearance() {

        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setBackground(BACKGROUND_COLOR);

    }

    /**
     * Draw lines on the graph where major world events happened
     *
     * @param g2
     * @param index
     * @param x
     * @param x1
     */
    public void markEvents(Graphics2D g2, int index, double x, double x1) {

        switch (loader.getData().get(index).getQuoteDate().toString()) {

            case "Mon Jan 03 00:00:00 CET 2000":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("Dotcom", (int) x, MARGIN / 2);
                break;

            case "Mon Sep 10 00:00:00 CEST 2001":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("9/11", (int) x, MARGIN / 2);
                break;

            case "Mon Sep 15 00:00:00 CEST 2008":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("08 Financial Crisis", (int) x, MARGIN / 2);
                break;

            case "Tue Apr 27 00:00:00 CEST 2010":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("Greek Crisis", (int) x, MARGIN / 4);
                break;

            case "Fri Mar 11 00:00:00 CET 2011":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("Fukushima", (int) x, MARGIN / 2);
                break;

            case "Mon Aug 01 00:00:00 CEST 2011":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("Eurocrisis", (int) x, MARGIN);
                break;

            case "Fri Jun 12 00:00:00 CEST 2015":
                g2.drawLine((int) x, MARGIN, (int) x1, getHeight() - MARGIN);
                g2.drawString("Greek Crisis 2.0", (int) x, MARGIN / 2);
                break;

        }

    }

    /**
     * Draw lines whith quote data
     *
     * @param g2 graphics context
     */
    public void drawGraphLine(Graphics2D g2) {

        double y, y1, x, x1, panelX, panelY;

        panelX = this.getWidth();
        panelY = this.getHeight();

        g2.setColor(Color.red);

        for (int i = 1; i < loader.getData().size(); i++) {

            y = calculator.createCoordinate(i, panelY, ECoordinates.Y);
            y1 = calculator.createCoordinate(i, panelY, ECoordinates.Y1);
            x = calculator.createCoordinate(i, panelX, ECoordinates.X);
            x1 = calculator.createCoordinate(i, panelX, ECoordinates.X1);

            g2.drawLine((int) x, (int) y, (int) x1, (int) y1);

            markEvents(g2, i, x, x1);

        }

    }

    /**
     * Draws the x-axis with time data. TODO: is not at all working as intended
     * and needs to be generalized
     *
     * @param g2 graphics context
     */
    public void drawXAxis(Graphics2D g2) {

        g2.drawLine(MARGIN, getHeight() - MARGIN, getWidth() - MARGIN, getHeight() - MARGIN);

        int year = 2000;

        for (int i = 10; i < (17 * 12) + 11; i++) {

            int xDistance = (i - 11) * (getWidth() - MARGIN * 2) / ((16 * 12) + 3) + MARGIN;
            int xDistance1 = xDistance;
            int yDistance = getHeight() - MARGIN;
            int yDistance1 = yDistance - 5;

            if (i % 12 == 0) {

                g2.drawString("" + year, xDistance, yDistance - 6);
                g2.drawLine(xDistance, yDistance, xDistance1, yDistance1);
                year += 1;

            }

        }
    }

    /**
     * Draws the y-axis. TODO: is not at all working as intended and needs to be
     * generalized
     *
     * @param g2 graphics context
     */
    public void drawYAxis(Graphics2D g2) {

        int marker = 0;

        g2.drawLine(MARGIN, getHeight() - MARGIN, MARGIN, MARGIN);

        for (int i = 0; i < 12; i++) {

            int xDistance = MARGIN;
            int xDistance1 = 5 + MARGIN;
            int yDistance = getHeight() - (((i + 1) * (getHeight() - MARGIN * 2)) / 12 + MARGIN);
            int yDistance1 = yDistance;

            marker += 1000;
            g2.drawLine(xDistance, yDistance, xDistance1, yDistance1);
            g2.drawString("" + marker, xDistance + 6, yDistance);

        }

    }

    /**
     * Paint method override. Excecutes all the painting methods
     *
     * @param g graphics context
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
