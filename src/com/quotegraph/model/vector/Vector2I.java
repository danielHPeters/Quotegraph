package com.quotegraph.model.vector;

import com.quotegraph.interfaces.IVector2I;

/**
 * 2D int vector implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class Vector2I implements IVector2I {
  private int x;
  private int y;

  public Vector2I(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void add(IVector2I vector) {
    this.x += vector.getX();
    this.y += vector.getY();
  }

  @Override
  public void sub(IVector2I vector) {
    this.x -= vector.getX();
    this.y -= vector.getY();
  }

  @Override
  public void mult(int scalar) {
    this.x *= scalar;
    this.y *= scalar;
  }

  @Override
  public void div(int scalar) {
    this.x /= scalar;
    this.y /= scalar;
  }

  @Override
  public int mag() {
    return (int) Math.sqrt(this.x * this.x + this.y * this.y);
  }

  @Override
  public void normalize() {

    int magnitude = mag();

    if (magnitude != 0) {
      div(magnitude);
    }
  }

  @Override
  public void limit(int max) {

    if (this.mag() > max) {

      this.normalize();
      this.mult(max);
    }
  }

  @Override
  public void set(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void set(IVector2I vector) {
    this.x = vector.getX();
    this.y = vector.getY();
  }
}