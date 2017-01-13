package com.quotegraph.view;

import com.quotegraph.model.DataLoader;
import com.quotegraph.controller.DropDownAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Diese Klasse erzeugt ein Dropdown aus einer Dateiliste.
 *
 *
 * @author d.peters
 */
public class DataSelect extends JComboBox<String> {

    private final DropDownAction action;

    /**
     * Mit diesem Objekt können die Daten geholt werden und zugehörige
     * Funktionen ausgeführt werden
     */
    private final DataLoader laden;

    /**
     * Konstruktor, der bei Aufruf den Dropdwon erzeugt mit den angegebenen
     * Werten
     *
     * @param laden
     * @param action
     */
    public DataSelect(DataLoader laden, DropDownAction action) {
        this.laden = laden;
        this.action = action;
        String[] dateinamen = {"vw", "blackrock", "goldman", "cac40"};
        setModel(new DefaultComboBoxModel<>(dateinamen));
        setSelectedIndex(0);
        addActionListener(action);
    }

}
