package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;

public class Spider extends Organism {
    Spider(int x, int y) {
        super("X", x, y);
    }

    public boolean moveEat(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        Organism next;
        while (i.hasNext()) {
            next = i.next();
            // send message of what organism is here
            if (next instanceof Ant) {
                // move to this location
                return true;
            }
        }
        return false;
    }

    public boolean move(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        Organism next;
        while (i.hasNext()) {
            next = i.next();
            // send message of what organism is here
            if (next instanceof Spider) {
                return false;
            } else if (next instanceof Ant)
                return false;
        }
        return true;
    }

    public void reproduce() {

    }

    public void die() {

    }
}
