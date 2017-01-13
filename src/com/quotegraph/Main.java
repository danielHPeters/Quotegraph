package com.quotegraph;

import com.quotegraph.model.DataLoader;
import com.quotegraph.controller.MysqlLoader;
import com.quotegraph.controller.FileLoader;
import com.quotegraph.controller.CoordinatesCalculator;
import com.quotegraph.controller.DropDownAction;
import com.quotegraph.view.DataSelect;
import com.quotegraph.view.LineGraph;
import com.quotegraph.view.ColumnGraph;
import com.quotegraph.view.CandleStickGraph;
import com.quotegraph.view.UserInterface;
import java.awt.BorderLayout;
import javax.swing.*;

/**
 * In dieser Klasse wird das Programm gestartet. Sonst sollte hier kein anderer
 * Code sein.
 *
 * @author d.peters
 */
public class Main {

    private JPanel graph;
    private DataLoader loader;
    private CoordinatesCalculator calculator;
    private DropDownAction action;
    private DataSelect dropDown;
    private UserInterface ui;

    public Main() {
        this.loader = new MysqlLoader("localhost", "root", "", "boersendaten");

        if (this.loader.hasFailed()) {

            System.out.println("Trying to use files as fallback.");

            this.loader = new FileLoader();

            if (this.loader.hasFailed()) {

                System.out.println(
                        "No data could be loaded at the Moment. "
                        + "Please try again later."
                );

            } else {
                System.out.println("Using files as data source");
            }

        } else {

            System.out.println("Using MySql as data source.");

        }

        if (!this.loader.hasFailed()) {

            this.calculator = new CoordinatesCalculator(loader);
            this.graph = new LineGraph(loader, calculator);
            //this.graph = new CandleStickGraph(loader);
            //this.graph = new ColumnGraph(loader);
            this.action = new DropDownAction(loader, graph);
            this.dropDown = new DataSelect(loader, action);
            this.ui = new UserInterface(dropDown);
            this.ui.add(graph, BorderLayout.CENTER);
            this.ui.pack();
            this.ui.setVisible(true);

        } else {
            int result;
            JFrame frame = new JFrame();
    
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            result = JOptionPane.showConfirmDialog(frame,
                    "Failed To Load any Data. Please check if the sources are available.",
                    "Error", JOptionPane.DEFAULT_OPTION);

            if (result == JOptionPane.YES_OPTION){
                frame.dispose();
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main program = new Main();
        });
    }
}
