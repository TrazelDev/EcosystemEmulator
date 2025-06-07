package Simulator;

import java.awt.*;

public class Simulator {
    public final static int DEFAULT_BOARD_ROWS = 10;
    public final static int DEFAULT_BOARD_COLUMNS = 10;
    private final Board simBoard;

    public Simulator(int boardX, int boardY, int numOfCarnivores, int numOfHerbivores, int numOfPlants)
            throws BoardNotBigEnoughException {
        simBoard = new Board(boardX, boardY);
        simBoard.placeOrganisms(numOfCarnivores, numOfHerbivores, numOfPlants);
        System.out.println("Starting board state:");
        simBoard.printBoard();
    }

    public Simulator(int numOfCarnivores, int numOfHerbivores, int numOfPlants)
            throws BoardNotBigEnoughException {
        simBoard = new Board(DEFAULT_BOARD_ROWS, DEFAULT_BOARD_COLUMNS);
        simBoard.placeOrganisms(numOfCarnivores, numOfHerbivores, numOfPlants);
        System.out.println("Starting board state:");
        simBoard.printBoard();
    }

    public void run(int numOfDays) {
        for (int i = 0; i < numOfDays; i++) {
            this.simBoard.iterateOrganisms(this::simulateOrganism);
            System.out.println("\nSimulator.Board after day " + i + ":");
            this.simBoard.printBoard();
        }
    }

    public void runDay() {
        this.simBoard.iterateOrganisms(this::simulateOrganism);
        this.simBoard.printBoard();
    }


    private void simulateOrganism(Organism organism)
    {
        if (organism instanceof Plant) ((Plant)organism).dailyEnergyUpdate();
        else simulateAnimal((Animal)organism);
    }

    private void simulateAnimal(Animal animal)
    {
        // If animal dies we remove it from the board and stop processing it
        if (animal.isDead()) {
            simBoard.relocateOrganism(animal, null);
            return;
        }

        Organism organismicFood = animal.eat(this.simBoard.getAdjacentOrganisms(animal));
        if (organismicFood != null)
            this.simBoard.relocateOrganism(organismicFood, null);

        Point newLocation = animal.move(this.simBoard.getAdjacentFreePoints(animal));
        if (newLocation != null)
            this.simBoard.relocateOrganism(animal, newLocation);
    }
}
