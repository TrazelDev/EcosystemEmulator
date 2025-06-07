package Simulator;

import java.awt.*;

public class Carnivore extends Animal
{
    public Carnivore(Point location)
    {
        super(location);
        this.name = 'C';
        this.foodName = 'H';
        this.energy = 40;
    }

    @Override
    protected void applyMovementEnergyPenalty()
    {
        this.energy -= 15;
    }
}
