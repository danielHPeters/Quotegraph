package com.quotegraph.interfaces;

/**
 * Interface for 2D double vectors.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public interface IVector2D {
  double getX();

  double getY();

  void add(IVector2D vector);

  void sub(IVector2D vector);

  void mult(double scalar);

  void div(double scalar);

  double mag();

  void normalize();

  void limit(double max);

  void set(double x, double y);

  void set(IVector2D vector);
}
