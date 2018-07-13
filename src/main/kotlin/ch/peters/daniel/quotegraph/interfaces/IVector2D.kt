package ch.peters.daniel.quotegraph.interfaces

/**
 * Interface for 2D double vectors.
 *
 * @author Daniel Peters
 * @version 1.0
 */
interface IVector2D {
  var x: Double
  var y: Double

  fun add( vector: IVector2D)

  fun sub( vector: IVector2D)

  fun mult(scalar: Double)

  fun div(scalar: Double)

  fun mag(): Double

  fun normalize()

  fun limit(max: Double)

  fun set(valX: Double, valY: Double)

  fun set(vector: IVector2D)
}
