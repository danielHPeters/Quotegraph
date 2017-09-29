package com.quotegraph.interfaces;

/**
 * @author Daniel Peters
 * @version 1.0
 */
public interface IVector2I {

    /**
     * @return
     */
    public int getX();

    /**
     * @return
     */
    public int getY();

    /**
     * @param vector
     */
    public void add(IVector2I vector);

    /**
     * @param vector
     */
    public void sub(IVector2I vector);

    /**
     * @param scalar
     */
    public void mult(int scalar);

    /**
     * @param scalar
     */
    public void div(int scalar);

    /**
     * @return
     */
    public int mag();

    /**
     *
     */
    public void normalize();

    /**
     * @param max
     */
    public void limit(int max);

    public void set(int x, int y);

    public void set(IVector2I vector);
}