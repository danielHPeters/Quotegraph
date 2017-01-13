package com.quotegraph.view;

import com.quotegraph.model.DataLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Mit dieser Klasse werden Candlesticks gezeichnet zu eingelesenen Werten.
 *
 * @version 0.3
 * @author d.peters
 */
public class CandleStickGraph extends JPanel {

    /**
     * Ursprungsdimension und Hintergrundfarbe der Zeichenfläche müssen hier
     * geändert werden.
     */
    private final int HOEHE = 600;
    private final int BREITE = 1000;
    private final Color HINTERGRUNDFARBE = Color.black;

    public DataLoader data;

    /**
     * Hintergrund und dimension der Zeichenoberfläche beim Aufruf
     * initialisieren. Achtung: Farbe und Dimensionen sind in Klassenattributen
     * gespeichert.
     *
     * @param daten
     */
    public CandleStickGraph(DataLoader daten) {
        this.data = daten;
        initAppearance();

    }

    private void initAppearance() {
        setPreferredSize(new Dimension(BREITE, HOEHE));
        setBackground(HINTERGRUNDFARBE);
    }

    /**
     * Diese Methode färbt die Candlesticks ein. Wenn der Tag im Plus
     * geschlossen hat, dann Grun. Wenn negativ dann rot. Bei unveränderten
     * Werten, dann weiss.
     *
     * @param open Aktueller Startwert.
     * @param close Aktueller Schlusswert.
     * @return Die zum Kurs passende Farbe.
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
     * Berchnen des oberen Schattens vom Candlestick
     *
     * @param high Aktueller Höchstwert
     * @param openOrClose Aktueller Startwert oder Schlusswert.
     * @return high - openOrClose
     */
    public double getTopLineLength(double high, double openOrClose) {

        return high - openOrClose;
    }

    /**
     * Länge des unteren Schattens
     *
     * @param openOrClose Aktueller Start oder Schlusswert
     * @param low Aktueller Niedrigstwert
     * @return openOrClose - low
     */
    public double getLowLineLength(double openOrClose, double low) {

        return openOrClose - low;
    }

    /**
     * Höehe der Kerze Berechnen. Bemerkung: Je nachdem, welcher Wert grösser
     * ist, ist entweder der Schlusswert oder der Startwert als erster Parameter
     *
     * @param openOrClose Aktueller Start oder Schlusswert
     * @param openOrClose1 Aktueller Start oder Schlusswert
     * @return
     */
    public double getRectHeight(double openOrClose, double openOrClose1) {

        return openOrClose - openOrClose1;
    }

    /**
     * Zeichnen einer Kerze
     *
     * @param g2 Graphics2D objekt zum Zeichnen
     * @param open Aktueller Startwert
     * @param close Aktueller Schlusswert
     * @param high Aktueller Höchstwert
     * @param low Aktueller Niedrigstwert
     * @param xMargin
     */
    public void drawCandle(Graphics2D g2, double open, double close, double high, double low, int xMargin) {

        double lineTopStartY = 50, lineTopEndY;
        double rectHeight;
        double lineBottomStartY, lineBottomEndY;
        double topLineLength, lowerLineLength;
        double skalierungsfaktor = 10;

        if (close > open) {
            topLineLength = getTopLineLength(high, close);
            lowerLineLength = getLowLineLength(open, low);
            rectHeight = getRectHeight(close, open) * skalierungsfaktor;

        } else {
            topLineLength = getTopLineLength(high, open);
            lowerLineLength = getLowLineLength(close, low);
            rectHeight = getRectHeight(open, close) * skalierungsfaktor;
        }

        lineTopEndY = lineTopStartY + topLineLength * skalierungsfaktor;
        lineBottomStartY = lineTopEndY + rectHeight;
        lineBottomEndY = lineBottomStartY + lowerLineLength * skalierungsfaktor;

        int rectWidth = 50;

        g2.setColor(candleColor(open, close));
        g2.drawLine(xMargin, (int) lineTopStartY, xMargin, (int) lineTopEndY);
        g2.fillRect(xMargin - rectWidth / 2, (int) lineTopEndY, rectWidth, (int) rectHeight);
        g2.drawLine(xMargin, (int) lineBottomStartY, xMargin, (int) lineBottomEndY);

    }

    /**
     * Hauptzeichnenfunktion von JPanel.<br>
     * Hier werden die Einzelnen Zeichenfunktionen aufgerufen.
     *
     * @param g Graphics Objekt zum Zeichnen
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        int xMargin = 0;
        for (int i = 1400; i < 1410; i++) {
            xMargin += 200;
            double open = Math.round(data.getData().get(i).getOpen());
            double close = Math.round(data.getData().get(i).getClose());
            double high = Math.round(data.getData().get(i).getHigh());
            double low = Math.round(data.getData().get(i).getLow());
            drawCandle(g2, open, close, high, low, xMargin);
        }
    }
}
