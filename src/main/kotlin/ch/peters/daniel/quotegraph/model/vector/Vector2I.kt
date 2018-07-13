package ch.peters.daniel.quotegraph.model.vector

import ch.peters.daniel.quotegraph.interfaces.IVector2I

/**
 * 2D int vector implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class Vector2I(override var x: Int, override var y: Int) : IVector2I {
  override fun add(vector: IVector2I) {
    x += vector.x
    y += vector.x
  }

  override fun sub(vector: IVector2I) {
    x -= vector.x
    y -= vector.x
  }

  override fun mult(scalar: Int) {
    x *= scalar
    y *= scalar
  }

  override fun div(scalar: Int) {
    x /= scalar
    y /= scalar
  }

  override fun mag(): Int {
    return Math.sqrt((x * x + y * y).toDouble()).toInt()
  }

  override fun normalize() {
    val magnitude = mag()

    if (magnitude != 0) {
      div(magnitude)
    }
  }

  override fun limit(max: Int) {
    if (mag() > max) {
      normalize()
      mult(max)
    }
  }

  override fun set(valX: Int, valY: Int) {
    x = valX
    y = valY
  }

  override fun set(vector: IVector2I) {
    x = vector.x
    y = vector.x
  }
}
