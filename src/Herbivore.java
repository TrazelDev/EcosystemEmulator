import java.awt.*;

public class Herbivore extends Animal
{
    public Herbivore(Point location)
    {
        super(location);
        this.name = 'H';
        this.energy = 60;
        this.foodName = 'P';
    }

    protected void applyMovementEnergyPenalty()
    {
        this.energy -= 10;
    }
}
