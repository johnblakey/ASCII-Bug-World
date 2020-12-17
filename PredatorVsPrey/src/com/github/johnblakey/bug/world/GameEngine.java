package com.github.johnblakey.bug.world;

public class GameEngine {
    private Grid grid;

    public static void main(String[] args) {
        System.out.println("Bug World");

        GameEngine game = new GameEngine();
        game.start();
    }

    public void start() {
        // Grid represents the world
        grid = new Grid(10);
        placeOrganisms();
        print();
        moveTurn();
        behaviorTurn();
        print();
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
    }

    private void behaviorTurn() {
        // Organism behavior is evaluated in each square
        grid.evaluateLoop();
    }

    private void print() {
        // Clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();
        grid.print();
    }

    // while loop needed for running simulation
}
