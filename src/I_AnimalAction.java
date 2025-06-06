import java.awt.*;

public interface I_AnimalAction
{
    public Organism eat(Organism[] potentialFood);
    public Point move(Point[] potentialNewLocations);
    public boolean isDead();
}
