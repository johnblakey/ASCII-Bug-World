package com.github.johnblakey.bug.world;

public class GameEngine {
    private Grid grid;

    public void start() {
        // Grid represents the world
        grid = new Grid(10);
        placeOrganisms();
        moveTurn();
        actionTurn();
        turnTest();
    }

    private void placeOrganisms() {
        Organism ant = new Ant(0,1);
        grid.addOrganism(ant);

        Organism plant = new Plant(5, 5);
        grid.addOrganism(plant);

        Organism spider = new Spider(9, 9);
        grid.addOrganism(spider);
    }

    private void moveTurn() {
        // Organisms move per there characteristics
        grid.moveLoop();
        // Organism behavior is evaluated in each square
        grid.evaluateLoop();
    }

    private void actionTurn() {

    }

    private void turnTest() {

        grid.print();
    }

    // while loop needed for running simulation
}
