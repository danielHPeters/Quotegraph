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
 * Display data as a column graph. TODO: Scaling needs to be improved.
 * Formula for for scaling coordinates: x = (actualValX - MinActualValX) *
 * windowWidth / (MaxActualValX - MinActualValX); y = (actualValY -
 * MinActualValY) * windowHeight / (MaxActualValY - MinActualValY);
 *
 * @author d.peters
 */
public class ColumnGraph extends JPanel {

  /**
   * Width of the JPanel.
   */
  private final int defaultWidth = 1000;

  /**
   * Height of the JPanel.
   */
  private final int defaultHeight = 600;

  /**
   * Background color of the JPanel.
   */
  private final Color backgroundColor = Color.yellow;

  /**
   * Defines the Margin around the graph.
   */
  private final int margin = 40;

  /**
   * Coordinates calculator.
   */
  private final CoordinatesCalculator calculator;

  /**
   * Default constructor. Initializes the dimension and color of canvas.
   *
   * @param loader data loader instance reference
   */
  public ColumnGraph(DataLoader loader) {
    this.calculator = new CoordinatesCalculator(loader);
    initAppearance();

  }

  private void initAppearance() {
    this.setPreferredSize(new Dimension(defaultWidth, defaultHeight));
    this.setBackground(backgroundColor);
  }

  private void paintXAxis(Graphics2D g2) {
    g2.setColor(Color.blue);
    g2.drawLine(margin, getHeight() - margin, getWidth() - margin, getHeight() - margin);

    for (int i = 0; i < 20 - 1; i++) {
      int marginX = (i + 1) * (getWidth() - margin * 2) / (20 - 1) + margin;
      int marginY = getHeight() - margin;
      int marginY1 = marginY - 5;
      g2.drawLine(marginX, marginY, marginX, marginY1);
    }
  }

  private void paintYAxis(Graphics2D g2) {
    g2.setColor(Color.blue);
    g2.drawLine(margin, getHeight() - margin, margin, margin);

    for (int i = 0; i < 10; i++) {
      int marginY = getHeight() - (((i + 1) * (getHeight() - margin * 2)) / 10 + margin);
      g2.drawLine(margin, marginY, margin + 5, marginY);
    }
  }

  private void drawColumns(Graphics2D g2) {
    int columnWidth = 1;
    double ratio = Math.round((getWidth() - 2 * margin) / 100 - 1);

    g2.setColor(Color.red);
    for (int counter = 1; counter < 100; counter++) {
      double positionX = (counter - 1) * ratio;
      double columnHeight = calculator.createCoordinate(counter, getHeight(), ECoordinates.Y1);
      int windowHeight = getHeight() - margin;
      g2.drawRect(
          (int) positionX + margin,
          windowHeight - (int) columnHeight,
          (int) (columnWidth * ratio),
          (int) columnHeight);

    }
  }

  /**
   * Drawing done here.
   *
   * @param g graphics context.
   */
  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    super.paintComponent(g2);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    drawColumns(g2);
    paintXAxis(g2);
    paintYAxis(g2);
  }
}
