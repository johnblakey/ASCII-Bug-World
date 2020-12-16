package com.github.johnblakey.bug.world;

public class GameEngine {
    private Grid grid;

    public void start() {
        // Grid represents the world
        grid = new Grid(10);
        turnTest();
    }

    private void turnTest() {
        Organism ant = new Ant();
        grid.addOrganism(0, 1, ant);

        Organism plant = new Plant();
        grid.addOrganism(5, 5, plant);

        Organism spider = new Spider();
        grid.addOrganism(9, 9, spider);

        grid.print();
    }
}
