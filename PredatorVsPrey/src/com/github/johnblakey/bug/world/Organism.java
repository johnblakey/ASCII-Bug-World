package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

abstract class Organism {
    private String symbol = " ";
    private int x;
    private int y;
    private boolean isDone;
    private boolean hasEaten = false;

    private int reproduceTurns;
    private int reproduceTurnsLeft;

    private int starveTurns;
    private int starveTurnsLeft;

    private int eatTurns;
    private int eatTurnLeft;

    EatBehavior eatBehavior;

    Organism(String symbol, int x, int y, int reproduceTurns, int starveTurns, int eatTurns) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;

        this.reproduceTurns = reproduceTurns;
        this.reproduceTurnsLeft = reproduceTurns;

        this.starveTurns = starveTurns;
        this.starveTurnsLeft = starveTurns;

        this.eatTurns = eatTurns;
        this.eatTurnLeft = eatTurns;
    }

    public String getSymbol() {
        return symbol;
    };

    public boolean shouldReproduce() {
        if (reproduceTurnsLeft <= 0 && getHasEaten()) {
            resetReproductionTurnsLeft();
            resetHasEaten();
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

        decrementStarveTurnsLeft();
        decrementReproductionTurnsLeft();
        decrementEatTurnsLeft();

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

    // TODO refactor injecting object not good design
    public void performEat() {
        eatBehavior.eat(this);
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

    public void decrementStarveTurnsLeft() {
        --starveTurnsLeft;
    }

    public void resetStarveTurnsLeft() {
        starveTurnsLeft = starveTurns;
    }

    private void decrementReproductionTurnsLeft() {
        --reproduceTurnsLeft;
    }

    private void resetReproductionTurnsLeft() {
        reproduceTurnsLeft = reproduceTurns;
    }

     private void decrementEatTurnsLeft() {
        --eatTurnLeft;
    }

    public void resetEatTurnsLeft() {
        eatTurnLeft = eatTurns;
    }

    public int getEatTurnsLeft() {
        return eatTurnLeft;
    }

    public boolean getHasEaten() {
        return hasEaten;
    }

    public void setHasEatenTrue() {
        hasEaten = true;
    }

    public void resetHasEaten() {
        hasEaten = false;
    }
}
