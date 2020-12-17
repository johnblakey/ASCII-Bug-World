package com.github.johnblakey.bug.world;

public class Ant extends Organism {
    Ant(int x, int y) {
        super("8", x, y);
    }

    public void move() {
        checkAdjacentSquares();
    }

    private void checkAdjacentSquares() {

    }

    public void reproduce() {

    }

    public void die() {

    }
}
