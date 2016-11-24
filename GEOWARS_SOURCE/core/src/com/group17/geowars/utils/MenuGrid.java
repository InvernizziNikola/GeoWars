package com.group17.geowars.utils;

/**
 * Created by nikola on 24/11/2016.
 */
public class MenuGrid {
    int x = 0;
    int y = 0;

    public MenuGrid(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public boolean equals(MenuGrid o)
    {
        if(this.x == o.x && this.y == o.y)
            return true;

        return  false;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
