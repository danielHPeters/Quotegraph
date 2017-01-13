package com.quotegraph.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Diese Klasse fügt die verschiedenen Komponente zusammen und erzeugt so die
 * Benutzeroberfläche.
 *
 * @author d.peters
 */
public class UserInterface extends JFrame{
    
    private final DataSelect dropdown;
    /**
     * Fenster bereits im Konstruktor erzeugen.
     * @param dropdown
     */
    public UserInterface (DataSelect dropdown){
        this.dropdown = dropdown;
        initAppearance();
    }
    
    private void initAppearance(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Diagrammfläche");
        this.add(dropdown, BorderLayout.NORTH);
        this.setLocationByPlatform(true);
    }
    
    

}