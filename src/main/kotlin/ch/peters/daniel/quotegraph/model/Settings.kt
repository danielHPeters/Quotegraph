package ch.peters.daniel.quotegraph.model

class Settings {
  private val panelWidth: Int = 1000
  private val panelHeight: Int = 600
  private val margins: Map<String, Double>

  init {
    this.margins = HashMap()
    this.margins.put("top", 10.0)
    this.margins.put("bottom", 10.0)
    this.margins.put("left", 10.0)
    this.margins.put("right", 10.0)
  }
}
