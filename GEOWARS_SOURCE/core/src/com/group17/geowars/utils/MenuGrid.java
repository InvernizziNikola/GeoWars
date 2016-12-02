package com.group17.geowars.utils;

/**
 * Created by nikola on 24/11/2016.
 */
public class MenuGrid {

    private final int x;
    private final int y;

    public int X ()
    {
        return x;
    }
    public int Y ()
    {
        return y;
    }
    public MenuGrid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuGrid)) return false;
        MenuGrid key = (MenuGrid) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

