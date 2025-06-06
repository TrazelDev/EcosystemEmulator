import java.awt.*;

public class Plant extends Organism
{
    public Plant(Point location)
    {
        super(location);
        this.name = 'P';
        this.energy = 20;
    }

    public void dailyEnergyUpdate()
    {
        this.energy = this.energy + 10;
    }
}
