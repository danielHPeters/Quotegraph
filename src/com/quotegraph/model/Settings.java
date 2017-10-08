package com.quotegraph.model;

import java.util.HashMap;
import java.util.Map;

public class Settings {
  private int panelWidth;
  private int panelHeight;
  private Map<String, Double> margins;

  /**
   * Default constructor. Initializes settings.
   */
  public Settings() {
    this.panelWidth = 1000;
    this.panelHeight = 600;
    this.margins = new HashMap<>();
    this.margins.put("top", 10d);
    this.margins.put("bottom", 10d);
    this.margins.put("left", 10d);
    this.margins.put("right", 10d);
  }
}
