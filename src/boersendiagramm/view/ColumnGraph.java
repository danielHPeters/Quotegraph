package boersendiagramm.view;

import boersendiagramm.model.DataLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Diese Klasse stellt eingelesene Daten in einem Säulendiagramm dar.
 *
 * @author d.peters
 */
public class ColumnGraph extends JPanel {

    /**
     * Initialhöhe und Breite des Zeichenoberfläche definieren
     */
    private final int HOEHE = 600;
    private final int BREITE = 1000;
    //Hintergrundfarbe der Zeichenoberfläche
    private final Color HINTERGRUNDFARBE = Color.yellow;

    //Abstände von LinienGrafik zum Rand hier definieren
    private final int SEITENABSTAND = 40;

    /**
     * Strings um zu Prüfen, welcher Koordinat gerade berechnet werden muss Bem
     * übergeben der Werte zu der nächsten Funktion
     */
    private final String Y_RECHNEN = "y";
    private final String Y1_RECHNEN = "y1";
    private final String X_RECHNEN = "x";
    private final String X1_RECHNEN = "x1";

    public DataLoader daten;

    /**
     * Setzt das Aussehen und Dimension der Zeichenoberfläche bei Aufruf.
     *
     * @param daten
     */
    public ColumnGraph(DataLoader daten) {
        this.daten = daten;
        initAppearance();
    }

    private void initAppearance() {
        this.setPreferredSize(new Dimension(BREITE, HOEHE));
        this.setBackground(HINTERGRUNDFARBE);
    }

    //Weltwerte runden, damit es GanzzahlKoordinaten gibt
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
        if (koordinatTyp.equals(Y_RECHNEN) || koordinatTyp.equals(Y1_RECHNEN)) {
            //Minimaler- und maximaler Ywert aus ArrayList holen
            minwert = daten.getMinClose();
            maxwert = daten.getMaxClose();
            //Fensterhoehe Holen
            achse = getHeight();

            if (koordinatTyp.equals(Y_RECHNEN)) {
                //Y Startpunkt
                weltwert = daten.getData().get(zaehler - 1).getClose();
            } else {
                //Y Endpunkt
                weltwert = daten.getData().get(zaehler).getClose();
            }
        } else if (koordinatTyp.equals(X_RECHNEN) || koordinatTyp.equals(X1_RECHNEN)) {
            //Minimaler und maximaler x Wert
            minwert = daten.getMinTimeStamp();
            maxwert = daten.getMaxTimeStamp();
            //Aktuelle Fensterbreite holen
            achse = getWidth();
            if (koordinatTyp.equals(X_RECHNEN)) {
                //X Startpunkt
                weltwert = daten.getTimeStamps().get(zaehler - 1);
            } else {
                //X Endpunkt
                weltwert = daten.getTimeStamps().get(zaehler);
            }
        }

        return koordinatenBerechnen(achse, weltwert, minwert, maxwert, koordinatTyp);
    }

    /**
     * Formel für Koordinatenumwandlung auf Fenster: x = (weltwertx -
     * min-weltminwertx) * Fensterbreite / (max-weltwertx - min-weltwertx); y =
     * (weltwerty - min-weltwerty) * Fensterhoehe / (max-weltwerty -
     * min-weltwerty);
     */
    /**
     * Fensterkoordinaten ausrechnen
     *
     * @param fensterDimension
     * @param wert
     * @param minWert
     * @param maxWert
     * @param koorTyp
     * @return
     */
    private double koordinatenBerechnen(double fensterDimension, double wert, double minWert, double maxWert, String koorTyp) {

        double koor;

        /**
         * Verhältnis von Fensterwert zu Originalwer Abstände werden zu allen
         * Seiten mit einberechnet
         */
        double verhaeltnis = (fensterDimension - 2 * SEITENABSTAND) / (maxWert - minWert);
        /**
         * Da die Y Koordinaten im JPanel umgekehrt werden müssen, wird das
         * Resultat der Formel zwischengespeichert
         */
        double zwischenresultat = (wert - minWert) * verhaeltnis;

        //Abfrage, da y Achse umgekehrt werden muss.
        if (koorTyp.equals(Y_RECHNEN) || koorTyp.equals(Y1_RECHNEN)) {
            //Y-Achse umkehren und Abstand zum Rand einsetzen
            koor = Math.round(fensterDimension - zwischenresultat - 2 * SEITENABSTAND);
        } else {
            //Abstand zum Rand einsetzen
            koor = Math.round(zwischenresultat + SEITENABSTAND);
        }
        return koor;
    }

    /**
     * Zeichnet die Grafik.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);

        double saeulenhoehe;
        double xverhaeltnis = Math.round((getWidth() - 2 * SEITENABSTAND) / 100 - 1);
        double xpos;
        int saeulenbreite = 1;
        // Zeichnen
        for (int zaehler = 1; zaehler < 100; zaehler++) {
            xpos = (zaehler - 1) * xverhaeltnis;
            saeulenhoehe = koordinaten(zaehler, Y1_RECHNEN);

            int fensterhoehe = getHeight() - SEITENABSTAND;

            g2.drawRect((int) xpos + SEITENABSTAND, fensterhoehe - (int) saeulenhoehe, (int) (saeulenbreite * xverhaeltnis), (int) saeulenhoehe);

        }

        g2.setColor(Color.blue);

        //Y-Achse zeichnen
        g2.drawLine(SEITENABSTAND, getHeight() - SEITENABSTAND, SEITENABSTAND, SEITENABSTAND);

        //X-Achse zeichnen
        g2.drawLine(SEITENABSTAND, getHeight() - SEITENABSTAND, getWidth() - SEITENABSTAND, getHeight() - SEITENABSTAND);

        //Abstandmarkierungen auf Y-Achse
        for (int i = 0; i < 10; i++) {
            int xabst = SEITENABSTAND;
            int xabst1 = 5 + SEITENABSTAND;
            int yabst = getHeight() - (((i + 1) * (getHeight() - SEITENABSTAND * 2)) / 10 + SEITENABSTAND);
            int yabst1 = yabst;
            g2.drawLine(xabst, yabst, xabst1, yabst1);
        }

        //Abstandmarkierungen auf X-Achse
        for (int i = 0; i < 20 - 1; i++) {
            int xabst = (i + 1) * (getWidth() - SEITENABSTAND * 2) / (20 - 1) + SEITENABSTAND;
            int xabst1 = xabst;
            int yabst = getHeight() - SEITENABSTAND;
            int yabst1 = yabst - 5;
            g2.drawLine(xabst, yabst, xabst1, yabst1);
        }
    }
}
