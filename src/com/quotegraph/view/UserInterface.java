package com.quotegraph.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Create a JFrame container window and add a dropdown.
 *
 * @author d.peters
 */
public class UserInterface extends JFrame{
    
    /**
     * Reference to the data select dropdown
     */
    private final DataSelect dropdown;
    
    /**
     * Initializes window look and dropdown
     * @param dropdown Reference to the select dropdown object
     */
    public UserInterface (DataSelect dropdown){
        this.dropdown = dropdown;
        initAppearance();
    }
    
    /**
     * Initialize the looks and title of the window
     */
    private void initAppearance(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Graph Panel");
        this.add(dropdown, BorderLayout.NORTH);
        this.setLocationByPlatform(true);
        
    }
    
    

}