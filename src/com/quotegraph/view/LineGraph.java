package com.quotegraph.view;

import com.quotegraph.controller.CoordinatesCalculator;
import com.quotegraph.model.DataLoader;
import com.quotegraph.model.ECoordinates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Displays quote data as a linear graph.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class LineGraph extends JPanel {

  /**
   * Height of the graph panel.
   */
  private final int defaultHeight = 600;

  /**
   * Width of the graph panel.
   */
  private final int defaultWidth = 1000;

  /**
   * Background color of the graph panel.
   */
  private final Color backgroundColor = Color.yellow;

  /**
   * Margins around the graph.
   */
  private final int margin = 40;

  /**
   * Reference to the DataLoader object.
   */
  private final DataLoader loader;

  /**
   * Reference to coordinates calculator object.
   */
  private final CoordinatesCalculator calculator;

  /**
   * Default constructor which initializes the data and appearance of the panel.
   *
   * @param loader data loader instance reference
   */
  public LineGraph(DataLoader loader) {
    this.calculator = new CoordinatesCalculator(loader);
    this.loader = loader;
    initAppearance();
  }

  /**
   * Initialize the appearance of this panel.
   */
  private void initAppearance() {
    this.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
    this.setBackground(backgroundColor);
  }

  /**
   * Draw lines on the graph where major world events happened.
   *
   * @param g2    2D graphics context
   * @param index drawing index
   * @param x     x position
   * @param x1    end x position
   */
  private void markEvents(Graphics2D g2, int index, double x, double x1) {
    switch (loader.getData().get(index).getQuoteDate().toString()) {
      case "Mon Jan 03 00:00:00 CET 2000":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("Dotcom", (int) x, margin / 2);
        break;
      case "Mon Sep 10 00:00:00 CEST 2001":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("9/11", (int) x, margin / 2);
        break;
      case "Mon Sep 15 00:00:00 CEST 2008":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("08 Financial Crisis", (int) x, margin / 2);
        break;
      case "Tue Apr 27 00:00:00 CEST 2010":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("Greek Crisis", (int) x, margin / 4);
        break;
      case "Fri Mar 11 00:00:00 CET 2011":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("Fukushima", (int) x, margin / 2);
        break;
      case "Mon Aug 01 00:00:00 CEST 2011":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("Eurocrisis", (int) x, margin);
        break;
      case "Fri Jun 12 00:00:00 CEST 2015":
        g2.drawLine((int) x, margin, (int) x1, getHeight() - margin);
        g2.drawString("Greek Crisis 2.0", (int) x, margin / 2);
        break;
      default:
        break;
    }
  }

  /**
   * Draw lines with quote data.
   *
   * @param g2 graphics context
   */
  private void drawGraphLine(Graphics2D g2) {
    double panelX = this.getWidth();
    double panelY = this.getHeight();

    g2.setColor(Color.red);

    for (int i = 1; i < loader.getData().size(); i++) {
      double y = calculator.createCoordinate(i, panelY, ECoordinates.Y);
      double y1 = calculator.createCoordinate(i, panelY, ECoordinates.Y1);
      double x = calculator.createCoordinate(i, panelX, ECoordinates.X);
      double x1 = calculator.createCoordinate(i, panelX, ECoordinates.X1);

      g2.drawLine((int) x, (int) y, (int) x1, (int) y1);
      markEvents(g2, i, x, x1);
    }
  }

  /**
   * Draws the x-axis with time data.
   * TODO: is not at all working as intended and needs to be generalized.
   *
   * @param g2 graphics context
   */
  private void drawXAxis(Graphics2D g2) {
    int year = 2000;

    g2.drawLine(margin, getHeight() - margin, getWidth() - margin, getHeight() - margin);

    for (int i = 10; i < (17 * 12) + 11; i++) {
      int distanceX = (i - 11) * (getWidth() - margin * 2) / ((16 * 12) + 3) + margin;
      int distanceY = getHeight() - margin;
      int distanceY1 = distanceY - 5;

      if (i % 12 == 0) {
        g2.drawString("" + year, distanceX, distanceY - 6);
        g2.drawLine(distanceX, distanceY, distanceX, distanceY1);
        year += 1;
      }
    }
  }

  /**
   * Draws the y-axis.
   * TODO: is not at all working as intended and needs to be generalized
   *
   * @param g2 graphics context
   */
  private void drawYAxis(Graphics2D g2) {
    int marker = 0;

    g2.drawLine(margin, getHeight() - margin, margin, margin);

    for (int i = 0; i < 12; i++) {
      int distanceX = margin;
      int distanceX1 = 5 + margin;
      int distanceY = getHeight() - (((i + 1) * (getHeight() - margin * 2)) / 12 + margin);

      marker += 1000;
      g2.drawLine(distanceX, distanceY, distanceX1, distanceY);
      g2.drawString("" + marker, distanceX + 6, distanceY);
    }
  }

  /**
   * Paint method override. Executes all the painting methods.
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
