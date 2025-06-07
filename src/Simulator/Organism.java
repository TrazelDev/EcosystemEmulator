package Simulator;

import java.awt.*;

public abstract class Organism
{
    private static int count = 0;
    protected Point location;
    protected int id;
    protected int energy;
    protected char name;

    public Organism(Point location)
    {
        id = count++;
        name = ' ';
        this.location = location;
    }

    public int getEnergy()
    {
        return energy;
    }

    public int getId()
    {
        return id;
    }

    public char getName()
    {
        return name;
    }

    public Point getLocation()
    {
        return location;
    }
}