package ch.peters.daniel.quotegraph.interfaces

/**
 * Interface for 2D int vectors.
 *
 * @author Daniel Peters
 * @version 1.0
 */
interface IVector2I {
  var x: Int
  var y: Int

  fun add(vector: IVector2I)

  fun sub(vector: IVector2I)

  fun mult(scalar: Int)

  fun div(scalar: Int)

  fun mag(): Int

  fun normalize()

  fun limit(max: Int)

  fun set(valX: Int, valY: Int)

  fun set(vector: IVector2I)
}
