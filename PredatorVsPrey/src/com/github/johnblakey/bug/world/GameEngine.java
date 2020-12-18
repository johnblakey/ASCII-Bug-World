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

    private void start(int turns, int size) {
        // Grid represents the world
        grid = new Grid(size);
        placeOrganisms();
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
    private void placeOrganisms() {
        Organism ant = new Ant(0,0);
        grid.addOrganism(ant);

        Organism ant0 = new Ant(0,1);
        grid.addOrganism(ant0);

        Organism ant1 = new Ant(1,1);
        grid.addOrganism(ant1);

//        Organism plant1 = new Plant(0, 1);
//        grid.addOrganism(plant1);

        Organism spider1 = new Spider(1, 0);
        grid.addOrganism(spider1);
//
//        Organism spider2 = new Spider(1, 1);
//        grid.addOrganism(spider2);
//
//        Organism plant = new Plant(2, 2);
//        grid.addOrganism(plant);
//
//        Organism spider = new Spider(4, 4);
//        grid.addOrganism(spider);
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
