package com.quotegraph.view;

import com.quotegraph.model.DataLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Generates a Candlestick Graph
 *
 * @author d.peters
 */
public class CandleStickGraph extends JPanel {

    /**
     * Default width of the graph
     */
    private final int DEFAULT_WIDTH = 1000;

    /**
     * Default height of the graph
     */
    private final int DEFAULT_HEIGHT = 600;

    /**
     * Background color of the Graph
     */
    private final Color BACKGROUND_COLOR = Color.black;

    /**
     * Reference to the loader loader object. DataLoader is an interface
     */
    public DataLoader loader;

    /**
     * Default constructor. Initializes the reference to the DataLoader object
     * and the looks of this JPanel
     *
     * @param loader
     */
    public CandleStickGraph(DataLoader loader) {

        this.loader = loader;
        initAppearance();

    }

    /**
     * Initializes the dimension and the background color of this JPanel
     */
    private void initAppearance() {

        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(BACKGROUND_COLOR);

    }

    /**
     * Colors the candlestick by comparing the open and close values.
     *
     * @param open current open value
     * @param close current close value
     * @return color showing a drop or rise of the value
     */
    public Color candleColor(double open, double close) {

        Color candleColor;

        if (close > open) {

            candleColor = Color.green;

        } else if (close < open) {

            candleColor = Color.red;

        } else {

            candleColor = Color.white;

        }

        return candleColor;
    }

    /**
     * Calculate the top shadow of the candlestick
     *
     * @param high current high
     * @param openOrClose current open or close
     * @return high - openOrClose the difference between the two submitted
     * values
     */
    public double getTopLineLength(double high, double openOrClose) {

        return high - openOrClose;
    }

    /**
     * Calculate the bottom shadow of the candlestick
     *
     * @param openOrClose Aktueller Start oder Schlusswert
     * @param low Aktueller Niedrigstwert
     * @return openOrClose - low
     */
    public double getLowLineLength(double openOrClose, double low) {

        return openOrClose - low;
    }

    /**
     * Calculate the height of the candle.
     *
     * @param openOrClose current open or close
     * @param openOrClose1 current open or close
     * @return
     */
    public double getRectHeight(double openOrClose, double openOrClose1) {

        return openOrClose - openOrClose1;
    }

    /**
     * Drawing of a candlestick
     *
     * @param g2 Graphics2D drawing context
     * @param open current open
     * @param close current close
     * @param high current high
     * @param low curent low
     * @param xMargin margin on x dimension
     */
    public void drawCandle(Graphics2D g2, double open, double close, double high, double low, int xMargin) {

        double lineTopStartY = 50, lineTopEndY, rectHeight,
                lineBottomStartY, lineBottomEndY,
                topLineLength, lowerLineLength, scaleFactor = 10;

        if (close > open) {

            topLineLength = getTopLineLength(high, close);
            lowerLineLength = getLowLineLength(open, low);
            rectHeight = getRectHeight(close, open) * scaleFactor;

        } else {

            topLineLength = getTopLineLength(high, open);
            lowerLineLength = getLowLineLength(close, low);
            rectHeight = getRectHeight(open, close) * scaleFactor;

        }

        lineTopEndY = lineTopStartY + topLineLength * scaleFactor;
        lineBottomStartY = lineTopEndY + rectHeight;
        lineBottomEndY = lineBottomStartY + lowerLineLength * scaleFactor;

        int rectWidth = 50;

        g2.setColor(candleColor(open, close));
        g2.drawLine(xMargin, (int) lineTopStartY, xMargin, (int) lineTopEndY);
        g2.fillRect(xMargin - rectWidth / 2, (int) lineTopEndY, rectWidth, (int) rectHeight);
        g2.drawLine(xMargin, (int) lineBottomStartY, xMargin, (int) lineBottomEndY);

    }

    /**
     * Draw multiple candlesticks in loop
     *
     * @param g2 graphics context
     */
    public void candleDrawingLoop(Graphics2D g2) {

        int xMargin;
        double open, close, high, low;

        xMargin = 0;

        for (int i = 1400; i < 1410; i++) {

            open = Math.round(loader.getData().get(i).getOpen());
            close = Math.round(loader.getData().get(i).getClose());
            high = Math.round(loader.getData().get(i).getHigh());
            low = Math.round(loader.getData().get(i).getLow());
            xMargin += 200;

            drawCandle(g2, open, close, high, low, xMargin);

        }

    }

    /**
     * Main drawing function draws all items on the JPanel area
     *
     * @param g Graphics Objekt zum Zeichnen
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);
        candleDrawingLoop(g2);

    }
}
