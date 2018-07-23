package ch.peters.daniel.quotegraph.view

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.WindowConstants

/**
 * Create a JFrame container window and add a dropdown.
 *
 * @author Daniel Peters
 * @version 2.0
 */
class UserInterface(dropdown: DataSelect) : JFrame() {
  init {
    this.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    this.layout = BorderLayout()
    this.title = "Graph Panel"
    this.add(dropdown, BorderLayout.NORTH)
    this.isLocationByPlatform = true
  }
}
