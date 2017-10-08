package com.quotegraph.view;

import com.quotegraph.controller.DropDownAction;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Dropdown to toggle data sources.
 *
 * @author Daniel Peters
 */
public class DataSelect extends JComboBox<String> {

  private final DropDownAction action;

  /**
   * Default constructor. Initializes the reference to the actions for this
   * dropdown.
   *
   * @param action action for this dropdown
   * @param sources list of data sources.
   */
  public DataSelect(DropDownAction action, String[] sources) {
    this.action = action;
    setModel(new DefaultComboBoxModel<>(sources));
    setSelectedIndex(0);
    addActionListener(this.action);

  }

}
