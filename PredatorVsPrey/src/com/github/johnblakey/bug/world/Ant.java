package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;

public class Ant extends Organism {
    Ant(int x, int y) {
        super("8", x, y);
        eatBehavior = new Eat();

        int reproduceLimit = 5;
        setReproduceTurns(reproduceLimit);
        setReproduceTurnsLeft(reproduceLimit);

        int starveLimit = 10;
        setStarveTurns(starveLimit);
        setStarveTurnsLeft(starveLimit);
    }

    // TODO make random
    public boolean moveToEat(HashSet<Organism> square) {
        // Empty square has no food
        if (square.size() == 0)
            return false;

        Iterator<Organism> i = square.iterator();
        Organism next;

        boolean hasSpider = false;
        boolean hasPlant = false;

        while (i.hasNext()) {
            next = i.next();
            if (next instanceof Plant) {
                hasPlant = true;
            } else if (next instanceof Spider) {
                hasSpider = true;
            }
        }
        // Ant does not want to eat a plant with a spider on it
        if (hasPlant && !hasSpider)
            return true;
        else
            return false;
    }

    // TODO make random
    public boolean move(HashSet<Organism> square) {
        return validateIfReproduceOrMove(square);
    }

    public boolean validReproduceSquare(HashSet<Organism> square) {
        return validateIfReproduceOrMove(square);
    }

    private boolean validateIfReproduceOrMove(HashSet<Organism> square) {
        // Move to an empty square
        if (square.size() == 0) {
            return true;
        }

        Iterator<Organism> i = square.iterator();
        Organism next;

        boolean hasSpider = false;
        boolean hasAnt = false;

        while (i.hasNext()) {
            next = i.next();
            if (next instanceof Spider) {
                hasSpider = true;
            } else if (next instanceof Ant)
                hasAnt = true;
        }
        // Ant won't move or reproduce to a square to get eaten or beside another ant
        if (!hasSpider && !hasAnt)
            return true;
        else
            return false;
    }

    public Organism createOffspring(SquareCoordinates squareCoordinates) {
        return new Ant(squareCoordinates.getX(), squareCoordinates.getY());
    }
}
