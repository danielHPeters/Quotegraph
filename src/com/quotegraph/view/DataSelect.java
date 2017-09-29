package com.quotegraph.view;

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
     * Default constructor. Initializes the reference to the actions for this
     * dropdown
     *
     * @param action
     * @param fileNames
     */
    public DataSelect(DropDownAction action, String[] fileNames) {

        this.action = action;
        setModel(new DefaultComboBoxModel<>(fileNames));
        setSelectedIndex(0);
        addActionListener(this.action);

    }

}
