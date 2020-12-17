package com.github.johnblakey.bug.world;

import java.util.HashSet;

public class Plant extends Organism {
    Plant(int x, int y) {
        super("*", x, y);
        setReproduceTurns(3);
        setStarveTurns(10);
    }

    public boolean moveEat(HashSet<Organism> organisms) {
        return false;
    }

    public boolean move(HashSet<Organism> organisms) {
        return false;
    }

    public boolean reproduce() {
        return false;
    }

    public boolean  die() {
        return false;
    }
}
