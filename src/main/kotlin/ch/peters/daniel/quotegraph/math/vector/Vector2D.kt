package ch.peters.daniel.quotegraph.math.vector

import ch.peters.daniel.quotegraph.interfaces.IVector2D

/**
 * 2D double vector implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
class Vector2D(override var x: Double, override var y: Double) : IVector2D {
  override fun add(vector: IVector2D) {
    x += vector.x
    y += vector.x
  }

  override fun sub(vector: IVector2D) {
    x -= vector.x
    y -= vector.y
  }

  override fun mult(scalar: Double) {
    x *= scalar
    y *= scalar
  }

  override fun div(scalar: Double) {
    x /= scalar
    y /= scalar
  }

  override fun mag(): Double {
    return Math.sqrt(x * x + y * y)
  }

  override fun normalize() {
    val magnitude = mag()

    if (magnitude != 0.0) {
      div(magnitude)
    }
  }

  override fun limit(max: Double) {
    if (mag() > max) {
      normalize()
      mult(max)
    }
  }

  override fun set(valX: Double, valY: Double) {
    x = valX
    y = valY
  }

  override fun set(vector: IVector2D) {
    x = vector.x
    y = vector.y
  }
}
