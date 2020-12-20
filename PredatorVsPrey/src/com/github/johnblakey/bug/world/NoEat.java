package com.github.johnblakey.bug.world;

public class NoEat implements EatBehavior {
    public void eat(Organism organism) {
        // Do nothing - plants don't eat
    }
}
