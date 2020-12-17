package com.github.johnblakey.bug.world;

import java.util.HashSet;

abstract class Organism {
    private String symbol = " ";
    private int x;
    private int y;
    private boolean hasMoved;

    private int reproduceTurns;
    private int starveTurns;
    private int reproduceTurnsLeft;
    private int starveTurnsLeft;

    EatBehavior eatBehavior;

    Organism(String symbol, int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    };

    abstract boolean reproduce();

    abstract boolean die();

    abstract boolean move(HashSet<Organism> square);

    abstract boolean moveEat(HashSet<Organism> square);

    public void performEat() {
        eatBehavior.eat();
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }


    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean set) {
        hasMoved = set;
    }

    public int getReproduceTurns() {
        return reproduceTurns;
    }

    public void setReproduceTurns(int reproduceTurns) {
        this.reproduceTurns = reproduceTurns;
    }

    public int getStarveTurns() {
        return starveTurns;
    }

    public void setStarveTurns(int starveTurns) {
        this.starveTurns = starveTurns;
    }

    public int getReproduceTurnsLeft() {
        return reproduceTurnsLeft;
    }

    public void setReproduceTurnsLeft(int reproduceTurnsLeft) {
        this.reproduceTurnsLeft = reproduceTurnsLeft;
    }

    public int getStarveTurnsLeft() {
        return starveTurnsLeft;
    }

    public void setStarveTurnsLeft(int starveTurnsLeft) {
        this.starveTurnsLeft = starveTurnsLeft;
    }
}
