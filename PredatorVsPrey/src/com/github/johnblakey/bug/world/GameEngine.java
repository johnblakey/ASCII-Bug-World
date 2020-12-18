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
        Organism ant1 = new Ant(0,1);
        grid.addOrganism(ant1);

        Organism ant2 = new Ant(3,1);
        grid.addOrganism(ant2);

        Organism ant3 = new Ant(1,3);
        grid.addOrganism(ant3);

        Organism plant1 = new Plant(0, 1);
        grid.addOrganism(plant1);

        Organism plant2 = new Plant(2, 2);
        grid.addOrganism(plant2);

        Organism plant3 = new Plant(4, 4);
        grid.addOrganism(plant3);

        Organism plant4 = new Plant(2, 3);
        grid.addOrganism(plant4);

        Organism plant5 = new Plant(4, 0);
        grid.addOrganism(plant5);

        Organism plant6 = new Plant(3, 3);
        grid.addOrganism(plant6);

        Organism spider1 = new Spider(1, 0);
        grid.addOrganism(spider1);

        Organism spider2 = new Spider(3, 3);
        grid.addOrganism(spider2);

        Organism spider3 = new Spider(4, 4);
        grid.addOrganism(spider3);
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
