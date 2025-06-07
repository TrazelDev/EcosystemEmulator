package GUI;

@FunctionalInterface
public interface SimulatorCreator {
    void createSimulator(int numCarnivores, int numHerbivores, int numPlants);
}
