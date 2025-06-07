package Simulator;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Consumer;


public class Board {
    private final Organism[][] board;
    private final int rows;
    private final int cols;
    Random rand;
    long seed;

    public Board(int rows, int columns) {
        board = new Organism[rows][columns];
        this.rows = rows;
        this.cols = columns;

        // reset all board cells to null
        resetBoard();

        seed = System.nanoTime();
        rand = new Random(seed);

        System.out.println("Seed: " + seed);
    }

    // Set all cells on the board to null.
    // This is the initial state of the board
    // before organisms are placed.
    public void resetBoard() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                board[i][j] = null;
            }
        }
    }

    public Boolean isEmpty(Point p) {
        return board[p.y][p.x] == null;
    }

    // Place organisms on the board
    // inputs: the number of figures of each type to be placed
    public void placeOrganisms(int numOfCarnivores, int numOfHerbivores, int numOfPlants) throws BoardNotBigEnoughException {
        // Making sure that there are less than 80% organisms of total spots:
        double totalOrganisms = numOfCarnivores + numOfHerbivores + numOfPlants;
        double organismsPercentage = totalOrganisms / (rows * cols);
        if (organismsPercentage > 0.8) throw new BoardNotBigEnoughException();

        Point p;
        for (int i = 0; i < totalOrganisms; i++) {
            p = getNextRandomEmptyLocation();
            assert (p != null) : "The seed " + this.seed + " is invalid.";

            if (numOfCarnivores > 0) {
                this.board[p.y][p.x] = new Carnivore(p);
                numOfCarnivores--;
                continue;
            }
            if (numOfHerbivores > 0) {
                this.board[p.y][p.x] = new Herbivore(p);
                numOfHerbivores--;
                continue;
            }
            this.board[p.y][p.x] = new Plant(p);
        }
    }

    // print the current board state
    // Organisms are printed by name and their energy next to it
    // empty cells are printed as 0
    public void printBoard() {
        Organism organism;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j] != null) {
                    organism = board[i][j];
                    System.out.print(organism.name + "-");
                    System.out.print(organism.energy + " ");
                    continue;
                }
                System.out.print("0-00 ");
            }
            System.out.println();
        }
    }

    // Iterates over all organisms once
    // If board changes while iterating it will not rerun the same organism
    public void iterateOrganisms(Consumer<Organism> operate) {
        HashSet<Integer> simulatedOrganismsIds = new HashSet<>();
        Organism currOrg;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                currOrg = board[i][j];
                if (currOrg == null) continue;
                if (simulatedOrganismsIds.contains(currOrg.id)) continue;

                simulatedOrganismsIds.add(currOrg.id);
                operate.accept(currOrg);
            }
        }
    }

    // Gets an organism and places it in another location on the board
    // if the location is null then it deletes the organism
    public void relocateOrganism(Organism organism, Point newLocation) {
        Point organismLocation = organism.location;
        this.board[organismLocation.y][organismLocation.x] = null;
        if (newLocation == null) return;
        assert (organism instanceof Animal) : "Plants do not support relocation";

        ((Animal)organism).relocate(newLocation);
        this.board[newLocation.y][newLocation.x] = organism;
    }

    // Returns spaces that are free for organisms to move to in order of priority described in Simulator.Direction class
    public Point[] getAdjacentFreePoints(Organism organism)
    {
        Point[] adjacentPoints = getAdjacentPoints(organism);
        Point[] adjacentFreePoints = new Point[adjacentPoints.length];

        for (int i = 0; i < adjacentPoints.length; i++) {
            if (adjacentPoints[i] == null) continue;
            if (this.board[adjacentPoints[i].y][adjacentPoints[i].x] != null) continue;
            adjacentFreePoints[i] = adjacentPoints[i];
        }

        return adjacentFreePoints;
    }

    // Returns organisms near organism according to priority directions described in Simulator.Direction class
    public Organism[] getAdjacentOrganisms(Organism organism)
    {
        Point[] adjacentPoints = getAdjacentPoints(organism);
        Organism[] adjacentOrganisms = new Organism[adjacentPoints.length];

        for (int i = 0; i < adjacentPoints.length; i++) {
            if (adjacentPoints[i] != null) adjacentOrganisms[i] = this.board[adjacentPoints[i].y][adjacentPoints[i].x];
        }
        return adjacentOrganisms;
    }



    // Find a random empty cell on the board
    // returns a Point object if found or Null if not found
    private Point getNextRandomEmptyLocation() {
        Point p = new Point();
        long attempts = (long) this.rows * this.cols * 2;
        do {
            attempts--;
            p.x = rand.nextInt(this.rows);
            p.y = rand.nextInt(this.cols);
        }
        while (!isEmpty(p) && (attempts > 0));

        return ((attempts > 0) ? p : null);
    }

    private boolean isPointOnBoard(Point p)
    {
        return (p.y >= 0 && p.y < this.rows && p.x >= 0 && p.x < this.cols);
    }

    // returns array of the points in the direction of the organism that are on the
    // board based on the priorities described in the Simulator.Direction class
    // none existing point is described using null
    private Point[] getAdjacentPoints(Organism organism) {
        Point[] adjacentPoints = {null, null, null, null};
        Point OrganismLocation = organism.location;
        Point adjacentPoint;
        for (int i = 0; i < Direction.xDirectionChange.length; i++) {
            adjacentPoint = new Point(OrganismLocation.x + Direction.xDirectionChange[i], OrganismLocation.y + Direction.yDirectionChange[i]);
            if (isPointOnBoard(adjacentPoint)) adjacentPoints[i] = adjacentPoint;
        }

        return adjacentPoints;
    }
}