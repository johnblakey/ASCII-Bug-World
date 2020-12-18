package com.github.johnblakey.bug.world;

import java.util.Vector;
import java.util.Random;

public class GameEngine {
    private Grid grid;

    public static void main(String[] args) {
        GameEngine game = new GameEngine();

        // Default turns and size
        int turns = 10;
        int size = 10;

        if (args.length == 5) {
            game.start(args);
        } else
            System.out.println("Incorrect arguments given for bug world, did you add the 5 ints needed?!");
    }

    private void start(String[] args) {
        int turns = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);

        // Grid represents the world
        grid = new Grid(size);
        placeOrganisms(args);
        print();

        gameLoop(turns);
    }

    private void gameLoop(int turns) {
        for (int i = 1; i <= turns; i++) {
            moveTurn();
            behaviorTurn();
            print();
        }
    }

    // TODO random placement and option on how many of xyz to place
    private void placeOrganisms(String[] args) {
        int size = Integer.parseInt(args[1]);
        int ants = Integer.parseInt(args[2]);
        int spiders = Integer.parseInt(args[3]);
        int plants = Integer.parseInt(args[4]);

        // Create valid square coordinates
        Vector<SquareCoordinates> squaresVector = new Vector<>();
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                SquareCoordinates squareCoordinates = new SquareCoordinates(j, k);
                squaresVector.add(squareCoordinates);
            }
        }

        for (int i = 0; i < ants; i++) {
            SquareCoordinates randomEmptySquare = getRandomEmptySquare(squaresVector);
            Ant ant = new Ant(0, 0);    // dummy values
            createOrganism(randomEmptySquare, ant);
        }

        for (int i = 0; i < spiders; i++) {
            SquareCoordinates randomEmptySquare = getRandomEmptySquare(squaresVector);
            Spider spider = new Spider(0, 0);    // dummy values
            createOrganism(randomEmptySquare, spider);
        }

        for (int i = 0; i < plants; i++) {
            SquareCoordinates randomEmptySquare = getRandomEmptySquare(squaresVector);
            Plant plant = new Plant(0, 0);    // dummy values
            createOrganism(randomEmptySquare, plant);
        }

    }

    private SquareCoordinates getRandomEmptySquare(Vector<SquareCoordinates> squaresVector) {
        Random random = new Random();

        int max = squaresVector.size();
        int randomInt = random.nextInt(max);
        SquareCoordinates randomEmptySquare = squaresVector.get(randomInt);
        squaresVector.remove(randomInt);
        return randomEmptySquare;
    }

    private void createOrganism(SquareCoordinates squareCoordinates, Organism organism) {
        organism.setX(squareCoordinates.getX());
        organism.setY(squareCoordinates.getY());
        grid.addOrganism(organism);
    }

    private void moveTurn() {
        // Organisms move per their characteristics
        grid.moveGridOrganisms();
    }

    private void behaviorTurn() {
        // Organism behavior is evaluated in each square
        grid.gridOrganismsSquareActions();
    }

    private void print() {
        // Clear console in terminal (does not work in IDEA)
        System.out.print("\033[H\033[2J");
        System.out.flush();
        grid.print();
        System.out.print("\n\n");
    }
}
