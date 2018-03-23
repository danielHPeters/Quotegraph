package com.quotegraph

import com.quotegraph.controller.DbConfig
import com.quotegraph.controller.DropDownAction
import com.quotegraph.controller.FileLoader
import com.quotegraph.controller.SqlLoader
import com.quotegraph.interfaces.IDataLoader
import com.quotegraph.interfaces.ISqlConnection
import com.quotegraph.model.DayQuote
import com.quotegraph.view.DataSelect
import com.quotegraph.view.LineGraph
import com.quotegraph.view.UserInterface
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
 * TODO:
 * Improve column and candlestick graphs.
 * Let the user change the graph types via menu controls.
 *
 * @author d.peters
 * @version 13.01.2017
 */
class Main {
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

    if (loader.failed) {
      System.out.println("Trying to use files as fallback.")

      loader = FileLoader(defaultSource)
      if (loader.failed) {
        System.out.println("No data could be loaded at the Moment. Please try again later.")
      } else {
        data = loader.load()
        System.out.println("Using files as data source")
      }
    } else {
      data = loader.load()
      System.out.println("Using MySql as data source.")
    }
  }

  private fun initGui() {
    if (!loader.failed) {
      val sources = arrayOf("vw", "blackrock", "goldman", "cac40")
      graph = LineGraph(data)
      // graph = CandleStickGraph(data)
      // graph = ColumnGraph(data)
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
  private fun modifyLookAndFeel() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")
    } catch (e: Exception) {
      System.out.println("Failed to set look and feel.")
    }
    SwingUtilities.updateComponentTreeUI(ui)
  }

  init {
    initData()
    initGui()
    modifyLookAndFeel()
  }
}

fun main(args: Array<String>) {
  SwingUtilities.invokeLater({ Main() })
}
