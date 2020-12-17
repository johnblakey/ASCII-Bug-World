package com.github.johnblakey.bug.world;

abstract class Organism {
    private String symbol = " ";
    private int x;
    private int y;
    EatBehavior eatBehavior;

    Organism(String symbol, int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    };

    abstract void reproduce();

    abstract void die();

    abstract void move();

    public void performEat() {
        eatBehavior.eat();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
