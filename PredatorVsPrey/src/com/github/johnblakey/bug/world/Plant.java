package com.github.johnblakey.bug.world;

import java.util.HashSet;

public class Plant extends Organism {
    Plant(int x, int y) {
        super("*", x, y);
        eatBehavior = new NoEat();

        int reproduceLimit = 3;
        setReproduceTurns(reproduceLimit);
        setReproduceTurnsLeft(reproduceLimit);

        int starveLimit = 10;
        setStarveTurns(starveLimit);
        setStarveTurnsLeft(starveLimit);
    }

    public boolean moveToEat(HashSet<Organism> organisms) {
        return false;
    }

    public boolean move(HashSet<Organism> organisms) {
        return false;
    }

    public boolean validReproduceSquare() {
        return false;
    }
}
