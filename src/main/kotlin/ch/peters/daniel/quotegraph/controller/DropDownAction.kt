package ch.peters.daniel.quotegraph.controller

import ch.peters.daniel.quotegraph.interfaces.IDataLoader

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComboBox
import javax.swing.JPanel

/**
 * ActionListener, that waits for the user to select an item from the menu
 * dropdown, sets the selected source and tells the IDataLoader to load the
 * selected data.
 *
 * @author Daniel Peters
 */
class DropDownAction(private val loader: IDataLoader, private val canvas: JPanel) : ActionListener {
  override fun actionPerformed(ae: ActionEvent) {
    val cb =  ae.source as JComboBox<*>
    val location = "data/" + cb.selectedItem
    loader.source = location
    loader.load()
    canvas.repaint()
  }
}
