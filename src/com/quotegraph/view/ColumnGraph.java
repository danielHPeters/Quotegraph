package com.quotegraph.view;

import com.quotegraph.controller.CoordinatesCalculatorColumns;
import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Display data as a colum graph. TODO: Scaling needs to be improved.
 *
 * Formula for for scaling coordinates: x = (actualValX - MinActualValX) *
 * windowWidth / (MaxActualValX - MinActualValX); y = (actualValY -
 * MinActualValY) * windowHeight / (MaxActualValY - MinActualValY);
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
     *
     */
    private final CoordinatesCalculatorColumns calculator;

    /**
     * Setzt das Aussehen und Dimension der Zeichenoberfläche bei Aufruf.
     *
     * @param loader
     */
    public ColumnGraph(DataLoader loader) {

        this.calculator = new CoordinatesCalculatorColumns(loader);
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
            columnHeight = calculator.createCoordinate(counter, getHeight(), ECoordinates.Y1);
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
