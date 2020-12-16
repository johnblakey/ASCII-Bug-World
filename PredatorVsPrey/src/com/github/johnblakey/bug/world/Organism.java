package com.github.johnblakey.bug.world;

abstract class Organism {
    private String symbol = " ";

    Organism(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    };
}
