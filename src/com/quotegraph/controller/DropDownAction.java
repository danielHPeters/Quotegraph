package com.quotegraph.controller;

import com.quotegraph.model.DataLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * ActionListener, that waits for the user to select an item from the menu
 * dropdown, sets the selected source and tells the DataLoader to load the
 * selected data.
 *
 * @author d.peters
 */
public class DropDownAction implements ActionListener {

    /**
     * Reference to the DataLoader object, that loads the data
     */
    private final DataLoader loader;

    /**
     * Reference to the JPanel object where the drawing is done
     */
    private final JPanel panel;

    /**
     *
     * @param loader reference to DataLoader object
     * @param panel reference to JPanel object
     */
    public DropDownAction(DataLoader loader, JPanel panel) {
        this.loader = loader;
        this.panel = panel;
    }

    /**
     * Changes file or table to the one selected by user in the menu
     *
     * @param ae the triggering event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        JComboBox cb = (JComboBox) ae.getSource();
        String location = "data/" + (String) cb.getSelectedItem();

        loader.setSource(location);
        loader.load();
        panel.repaint();

    }

}
