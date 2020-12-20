package com.github.johnblakey.bug.world;

public class Eat implements EatBehavior {
    public void eat(Organism organism) {
        organism.resetStarveTurnsLeft();
        organism.resetEatTurnsLeft();
        organism.setHasEatenTrue();
    }
}
