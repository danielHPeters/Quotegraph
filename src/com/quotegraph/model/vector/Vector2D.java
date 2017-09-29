package com.quotegraph.model.vector;

import com.quotegraph.interfaces.IVector2D;

/**
 * @author Daniel Peters
 * @version 1.0
 */
public class Vector2D implements IVector2D {

    /**
     *
     */
    private double x, y;

    /**
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * @return
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * @param vector
     */
    @Override
    public void add(IVector2D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    /**
     * @param vector
     */
    @Override
    public void sub(IVector2D vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
    }

    /**
     * @param scalar
     */
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

    /**
     *
     */
    @Override
    public void normalize() {

        double magnitude = mag();

        if (magnitude != 0d) {
            div(magnitude);
        }
    }

    /**
     * @param max
     */
    @Override
    public void limit(double max) {

        if (this.mag() > max) {

            this.normalize();
            this.mult(max);
        }
    }

    /**
     * @param x
     * @param y
     */
    @Override
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param vector
     */
    @Override
    public void set(IVector2D vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }
}
