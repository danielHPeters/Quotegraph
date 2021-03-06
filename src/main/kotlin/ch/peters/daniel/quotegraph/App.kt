package ch.peters.daniel.quotegraph

import ch.peters.daniel.quotegraph.controller.DbConfig
import ch.peters.daniel.quotegraph.controller.DropDownAction
import ch.peters.daniel.quotegraph.controller.FileLoader
import ch.peters.daniel.quotegraph.controller.SqlLoader
import ch.peters.daniel.quotegraph.interfaces.IDataLoader
import ch.peters.daniel.quotegraph.model.DayQuote
import ch.peters.daniel.quotegraph.view.DataSelect
import ch.peters.daniel.quotegraph.view.LineGraph
import ch.peters.daniel.quotegraph.view.UserInterface
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.WindowConstants


/**
 * This Program displays data from various sources as graphs.
 * Right now, there are line graphs, column graphs and candlestick graphs available.
 * The most complete and functional one ist the line graph.
 * TODO: Improve column and candlestick graphs.
 * TODO: Let the user change the graph types via menu controls.
 *
 * @author Daniel Peters
 * @version 2.0
 */
class App {
  private var defaultSource: String = "data/vw"
  private lateinit var data: List<DayQuote>
  private lateinit var graph: JPanel
  private lateinit var loader: IDataLoader
  private lateinit var action: DropDownAction
  private lateinit var dropDown: DataSelect
  private lateinit var ui: UserInterface

  /**
   * Try to initialize the IDataLoader object, first try MySQL, then files.
   */
  private fun initData() {
    loader = SqlLoader(defaultSource, DbConfig("postgres", "localhost", "dp", "", "boersendaten", 5432))
    data = loader.load()
    if (loader.failed) {
      System.out.println("Trying to use files as fallback.")

      loader = FileLoader(defaultSource)
      data = loader.load()
      if (loader.failed) {
        System.out.println("No data could be loaded at the Moment. Please try again later.")
      } else {
        System.out.println("Using files as data source")
      }
    } else {
      System.out.println("Using MySql as data source.")
    }
  }

  private fun initGui() {
    if (!loader.failed) {
      val sources = arrayOf("vw", "blackrock", "goldman", "cac40")
      graph = LineGraph(data)
      action = DropDownAction(loader, graph)
      dropDown = DataSelect(action, sources)
      ui = UserInterface(dropDown)
      ui.add(graph, BorderLayout.CENTER)
      ui.pack()
      ui.isVisible = true
    } else {
      dataErrorDialog()
    }
  }

  /**
   * Displays a dialog with error message, when no data could be loaded.
   */
  private fun dataErrorDialog() {
    val frame = JFrame()
    val result: Int

    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    result = JOptionPane.showConfirmDialog(frame, "Failed To Load any Data..", "Error", JOptionPane.DEFAULT_OPTION)

    if (result == JOptionPane.YES_OPTION) {
      frame.dispose()
    }
  }

  /**
   * Change look and feel to a more modern look.
   */
  /**
   * Sets look and feel of the program ui.
   */
  private fun setLooks() {
    try {
      for (info in UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus" == info.name) {
          UIManager.setLookAndFeel(info.className)
          break
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  init {
    setLooks()
    initData()
    initGui()
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      SwingUtilities.invokeLater { App() }
    }
  }
}
