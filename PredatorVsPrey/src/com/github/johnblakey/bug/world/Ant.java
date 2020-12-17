package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;

public class Ant extends Organism {
    Ant(int x, int y) {
        super("8", x, y);
    }

    public boolean moveEat(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            // send message of what organism is here
            if (i.next() instanceof Plant) {
                // move to this location
                return true;
            }
        }
        return false;
    }

    public boolean move(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            // send message of what organism is here
            if (i.next() instanceof Spider) {
                // move to this location
                return false;
            } else if (i.next() instanceof Ant)
                return false;
        }
        return true;
    }

    public void reproduce() {

    }

    public void die() {

    }
}
