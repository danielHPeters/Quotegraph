package com.quotegraph.model.vector;


import com.quotegraph.interfaces.IVector2I;

/**
 * @author Daniel Peters
 * @version 1.0
 */
public class Vector2I implements IVector2I {

    /**
     *
     */
    private int x, y;

    /**
     * @param x
     * @param y
     */
    public Vector2I(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * @param vector
     */
    @Override
    public void add(IVector2I vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    /**
     * @param vector
     */
    @Override
    public void sub(IVector2I vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
    }

    /**
     * @param scalar
     */
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

    /**
     *
     */
    @Override
    public void normalize() {

        int magnitude = mag();

        if (magnitude != 0) {
            div(magnitude);
        }
    }

    /**
     * @param max
     */
    @Override
    public void limit(int max) {

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
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param vector
     */
    @Override
    public void set(IVector2I vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }
}