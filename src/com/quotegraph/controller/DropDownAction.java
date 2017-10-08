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
 * @author Daniel Peters
 */
public class DropDownAction implements ActionListener {
  private final DataLoader loader;
  private final JPanel canvas;

  /**
   * Default constructor.
   *
   * @param loader reference to DataLoader object
   * @param canvas reference to JPanel object
   */
  public DropDownAction(DataLoader loader, JPanel canvas) {
    this.loader = loader;
    this.canvas = canvas;
  }

  /**
   * Changes file or table to the one selected by user in the menu.
   *
   * @param ae the triggering event
   */
  @Override
  public void actionPerformed(ActionEvent ae) {
    JComboBox cb = (JComboBox) ae.getSource();
    String location = "data/" + cb.getSelectedItem();

    loader.setSource(location);
    loader.load();
    canvas.repaint();
  }
}
