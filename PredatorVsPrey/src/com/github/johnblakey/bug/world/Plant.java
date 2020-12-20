package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;

public class Plant extends Organism {
    Plant(int x, int y) {
        super("*", x, y);
        eatBehavior = new NoEat();

        int reproduceLimit = 8;
        setReproduceTurns(reproduceLimit);
        setReproduceTurnsLeft(reproduceLimit);

        int starveLimit = 25;
        setStarveTurns(starveLimit);
        setStarveTurnsLeft(starveLimit);
    }

    public boolean moveToEat(HashSet<Organism> organisms) {
        return false;
    }

    public boolean move(HashSet<Organism> organisms) {
        return false;
    }

    public boolean validReproduceSquare(HashSet<Organism> square) {
        // Reproduce to an empty square
        if (square.size() == 0) {
            return true;
        }

        Iterator<Organism> i = square.iterator();
        Organism next;

        boolean hasPlant = false;

        while (i.hasNext()) {
            next = i.next();
            if (next instanceof Plant) {
                hasPlant = true;
            }
        }
        // Do not reproduce to a square with a plant
        if (!hasPlant)
            return true;
        else
            return false;
    }

    public Organism createOffspring(SquareCoordinates squareCoordinates) {
        return new Plant(squareCoordinates.getX(), squareCoordinates.getY());
    }
}
