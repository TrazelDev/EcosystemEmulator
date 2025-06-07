package Simulator;

import java.awt.*;

abstract class Animal extends Organism implements I_AnimalAction
{
    protected Direction lastDirection;
    protected char foodName;

    public Animal(Point location)
    {
        super(location);
        lastDirection = new Direction();
    }

    @Override
    public Organism eat(Organism[] potentialFood)
    {
        for (Organism organism : potentialFood) {
            if (organism == null) continue;
            if (organism.name != this.foodName) continue;

            this.energy += organism.energy;
            return organism;
        }

        return null;
    }

    @Override
    public Point move(Point[] potentialNewLocations)
    {
        // Checking the previous location:
        int directionIndex = lastDirection.getDirection();
        if (potentialNewLocations[directionIndex] != null) {
            this.applyMovementEnergyPenalty();
            return potentialNewLocations[directionIndex];
        }

        for (int i = 0; i < potentialNewLocations.length; i++) {
            this.lastDirection.setDirection(i);
            if (potentialNewLocations[i] == null) continue;

            this.applyMovementEnergyPenalty();
            return potentialNewLocations[i];
        }

        return null;
    }

    @Override
    public boolean isDead()
    {
        return this.energy <= 0;
    }

    public void relocate(Point newLocation)
    {
        this.location = newLocation;
    }

    abstract protected void applyMovementEnergyPenalty();
}
