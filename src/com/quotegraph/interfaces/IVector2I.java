package com.quotegraph.interfaces;

/**
 * Interface for 2D int vectors.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public interface IVector2I {
  int getX();

  int getY();

  void add(IVector2I vector);

  void sub(IVector2I vector);

  void mult(int scalar);

  void div(int scalar);

  int mag();

  void normalize();

  void limit(int max);

  void set(int x, int y);

  void set(IVector2I vector);
}