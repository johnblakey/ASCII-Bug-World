package com.github.johnblakey.bug.world;

import java.util.HashSet;

public class Plant extends Organism {
    Plant(int x, int y) {
        super("*", x, y);
    }

    public boolean moveEat(HashSet<Organism> organisms) {
        return false;
    }

    public boolean move(HashSet<Organism> organisms) {
        return false;
    }

    public void reproduce() {

    }

    public void  die() {

    }
}
