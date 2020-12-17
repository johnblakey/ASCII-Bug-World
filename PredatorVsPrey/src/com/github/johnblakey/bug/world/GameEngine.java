package com.github.johnblakey.bug.world;

public class GameEngine {
    private Grid grid;

    public static void main(String[] args) {
        GameEngine game = new GameEngine();

        // Default turns and size
        int turns = 10;
        int size = 10;

        if (args.length > 1) {
            turns = Integer.parseInt(args[0]);
            size = Integer.parseInt(args[1]);
        }
        game.start(turns, size);
    }

    public void start(int turns, int size) {
        // Grid represents the world
        grid = new Grid(size);
        placeOrganisms();
        print();

        for (int i = 1; i <= turns; i++) {
            moveTurn();
            behaviorTurn();
            print();
        }
    }

    private void placeOrganisms() {
        Organism ant = new Ant(0,1);
        grid.addOrganism(ant);

//        Organism plant = new Plant(5, 5);
//        grid.addOrganism(plant);
//
//        Organism spider = new Spider(9, 9);
//        grid.addOrganism(spider);
    }

    private void moveTurn() {
        // Organisms move per their characteristics
        grid.moveLoop();
    }

    private void behaviorTurn() {
        // Organism behavior is evaluated in each square
        grid.actionLoop();
    }

    private void print() {
        // Clear console in terminal (does not work in IDEA)
        System.out.print("\033[H\033[2J");
        System.out.flush();
        grid.print();
        System.out.print("\n\n");
    }
}
