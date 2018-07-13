package ch.peters.daniel.quotegraph.view

import ch.peters.daniel.quotegraph.controller.DropDownAction

import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox

/**
 * Dropdown to toggle data sources.
 *
 * @author Daniel Peters
 */
class DataSelect(action: DropDownAction, sources: Array<String>) : JComboBox<String>() {
  init {
    model = DefaultComboBoxModel<String>(sources)
    selectedIndex = 0
    addActionListener(action)
  }
}
