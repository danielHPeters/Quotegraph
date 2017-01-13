package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Dropdown to select data from another company/currency
 *
 * @author d.peters
 */
public class DropDownAction implements ActionListener {

    /**
     *
     */
    private final DataLoader loader;

    /**
     *
     */
    private JPanel panel;

    /**
     *
     * @param loader
     * @param panel
     */
    public DropDownAction(DataLoader loader, JPanel panel) {
        this.loader = loader;
        this.panel = panel;
    }

    /**
     * Beim anwählen einer auswahl wird der dateiname vom Objekt Daten gesetzt.
     * Die angegebene Datei sollte dann geladen werden Bemerkung: Funktioniert
     * noch nicht
     *
     * @param ae Der action Event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        JComboBox cb = (JComboBox) ae.getSource();
        String location = (String) cb.getSelectedItem();
        loader.setSource(location);
        loader.load();
        panel.repaint();
    }

}
