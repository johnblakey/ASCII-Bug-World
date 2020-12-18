package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;

public class Spider extends Organism {
    Spider(int x, int y) {
        super("X", x, y);
        eatBehavior = new Eat();

        int reproduceLimit = 10;
        setReproduceTurns(reproduceLimit);
        setReproduceTurnsLeft(reproduceLimit);

        int starveLimit = 5;
        setStarveTurns(starveLimit);
        setStarveTurnsLeft(starveLimit);
    }

    // TODO make random
    public boolean moveEat(HashSet<Organism> square) {
        // Empty square has no food
        if (square.size() == 0)
            return false;

        Iterator<Organism> i = square.iterator();
        Organism next;

        boolean hasAnt = false;
        boolean hasSpider = false;

        while (i.hasNext()) {
            next = i.next();
            if (next instanceof Ant) {
                hasAnt = true;
            } else if (next instanceof Spider) {
                hasSpider = true;
            }
        }
        // Spider does not want to eat an ant with a spider on it
        if (hasAnt && !hasSpider)
            return true;
        else
            return false;
    }

    // TODO make random
    public boolean move(HashSet<Organism> square) {
        setStarveTurns(getStarveTurnsLeft() - 1);

        // Move to an empty square
        if (square.size() == 0) {
            return true;
        }

        Iterator<Organism> i = square.iterator();
        Organism next;

        boolean hasSpider = false;

        while (i.hasNext()) {
            next = i.next();
            if (next instanceof Spider) {
                hasSpider = true;
            }
        }
        // Spider won't move to a square with a spider
        if (!hasSpider)
            return true;
        else
            return false;
    }

    public boolean reproduceLocation() {
        return false;
    }
}
