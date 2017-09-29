package com.quotegraph.interfaces;

/**
 * @author Daniel Peters
 * @version 1.0
 */
public interface IVector2D {

    /**
     * @return
     */
    public double getX();

    /**
     * @return
     */
    public double getY();

    /**
     * @param vector
     */
    public void add(IVector2D vector);

    /**
     * @param vector
     */
    public void sub(IVector2D vector);

    /**
     * @param scalar
     */
    public void mult(double scalar);

    /**
     * @param scalar
     */
    public void div(double scalar);

    /**
     * @return
     */
    public double mag();

    /**
     *
     */
    public void normalize();

    /**
     * @param max
     */
    public void limit(double max);

    public void set(double x, double y);

    public void set(IVector2D vector);
}
