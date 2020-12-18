package com.github.johnblakey.bug.world;

import java.util.HashSet;

abstract class Organism {
    private String symbol = " ";
    private int x;
    private int y;
    private boolean isDone;

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

    public boolean reproduce() {
        if (reproduceTurnsLeft == 0) {
            setReproduceTurnsLeft(reproduceTurns);
            return true;
        }
        else
            return false;
    };

    abstract boolean reproduceLocation();

    public boolean die() {
        if (starveTurnsLeft == 0)
            return true;
        else
            return false;
    }

    abstract boolean move(HashSet<Organism> square);

    abstract boolean moveEat(HashSet<Organism> square);

    public void performEat() {
        eatBehavior.eat();
        setStarveTurnsLeft(starveTurns);
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


    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean set) {
        isDone = set;
    }

    public void setReproduceTurns(int reproduceTurns) {
        this.reproduceTurns = reproduceTurns;
    }

    public void setStarveTurns(int starveTurns) {
        this.starveTurns = starveTurns;
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
