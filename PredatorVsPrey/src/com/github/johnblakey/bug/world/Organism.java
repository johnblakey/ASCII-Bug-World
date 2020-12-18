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
            resetReproductionTurnsLeft();
            return true;
        }
        else
            return false;
    };

    abstract boolean validReproduceSquare(HashSet<Organism> square);

    public boolean die() {
        if (starveTurnsLeft == 0)
            return true;
        else
            return false;
    }

    abstract Organism createOffspring(SquareCoordinates squareCoordinates);

    abstract boolean moveToEat(HashSet<Organism> square);

    abstract boolean move(HashSet<Organism> square);

    public void performEat() {
        resetStarveTurnsLeft();
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

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean set) {
        isDone = set;
    }

    public void setReproduceTurns(int reproduceTurns) {
        this.reproduceTurns = reproduceTurns;
    }

    public void setReproduceTurnsLeft(int reproduceTurnsLeft) {
        this.reproduceTurnsLeft = reproduceTurnsLeft;
    }

    public void setStarveTurns(int starveTurns) {
        this.starveTurns = starveTurns;
    }

    public void setStarveTurnsLeft(int starveTurnsLeft) {
        this.starveTurnsLeft = starveTurnsLeft;
    }

    public void decrementStarveTurnsLeft() {
        --starveTurnsLeft;
    }

    public void decrementReproductionTurnsLeft() {
        --reproduceTurnsLeft;
    }

    private void resetReproductionTurnsLeft() {
        reproduceTurnsLeft = reproduceTurns;
    }

    private void resetStarveTurnsLeft() {
        starveTurnsLeft = starveTurns;
    }
}
