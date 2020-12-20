package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

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

    public SquareCoordinates getNewSquare(Vector<SquareCoordinates> validSquares, Grid grid) {
        if (getIsDone())
            return null;

        // The time to death and reproduction drops by one turn
        decrementStarveTurnsLeft();
        decrementReproductionTurnsLeft();

        // grab a random valid square (decision number input) - organism evaluates each if appropriate
        // moveToEat first, move next
        int decisionNumber = 15;
        for (int i = 0; i < decisionNumber; i++) {
            SquareCoordinates validSquare  = getRandomSquare(validSquares);
            // TODO refactor and remove dependency to grid
            if (moveToEat(grid.getSquareOrganisms(validSquare))) {
                return validSquare;
            }
        }

        for (int i = 0; i < decisionNumber; i++) {
            SquareCoordinates validSquare  = getRandomSquare(validSquares);
            if (move(grid.getSquareOrganisms(validSquare))) {
                return validSquare;
            }
        }
        // organism didn't move b/c no found valid location found
        return null;
    }

    private SquareCoordinates getRandomSquare(Vector<SquareCoordinates> squaresVector) {
        Random random = new Random();

        int max = squaresVector.size();
        int randomInt = random.nextInt(max);
        return squaresVector.get(randomInt);
    }

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
