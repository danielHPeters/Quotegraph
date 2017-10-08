package com.quotegraph.model.vector;

import com.quotegraph.interfaces.IVector2D;

/**
 * 2D double vector implementation.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class Vector2D implements IVector2D {
  private double x;
  private double y;

  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public double getX() {
    return this.x;
  }

  @Override
  public double getY() {
    return this.y;
  }

  @Override
  public void add(IVector2D vector) {
    this.x += vector.getX();
    this.y += vector.getY();
  }

  @Override
  public void sub(IVector2D vector) {
    this.x -= vector.getX();
    this.y -= vector.getY();
  }

  @Override
  public void mult(double scalar) {
    this.x *= scalar;
    this.y *= scalar;
  }

  @Override
  public void div(double scalar) {
    this.x /= scalar;
    this.y /= scalar;
  }

  @Override
  public double mag() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  @Override
  public void normalize() {

    double magnitude = mag();

    if (magnitude != 0d) {
      div(magnitude);
    }
  }

  @Override
  public void limit(double max) {

    if (this.mag() > max) {

      this.normalize();
      this.mult(max);
    }
  }

  @Override
  public void set(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void set(IVector2D vector) {
    this.x = vector.getX();
    this.y = vector.getY();
  }
}
