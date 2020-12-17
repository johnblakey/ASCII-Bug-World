package com.github.johnblakey.bug.world;

public class GameEngine {
    private Grid grid;

    public static void main(String[] args) {
        GameEngine game = new GameEngine();
        int turns = 0;
        if (args.length > 0) {
            turns = Integer.parseInt(args[0]);
        }
        game.start(turns);
    }

    public void start(int turns) {
        // Grid represents the world
        grid = new Grid(10);
        placeOrganisms();
        print();

        for (int i = 1; i <= turns; i++) {
            moveTurn();
            behaviorTurn();
            print();
        }

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
        // Clear console in terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
        grid.print();
        System.out.print("\n\n");
    }

    // while loop needed for running simulation
}
