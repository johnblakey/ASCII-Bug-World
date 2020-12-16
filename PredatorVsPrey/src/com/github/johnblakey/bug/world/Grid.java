package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;

public class Grid {
    private int size;
    private HashSet<Organism>[][] grid;

    public Grid(int size) {
        this.size = size;

        initGrid();
    }

    private void initGrid() {
        grid = new HashSet[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new HashSet<>();
            }
        }
    }

    public void print() {
        for (int x = 0; x < size; x++) {
            System.out.print("| ");
            for (int y = 0; y < size; y++) {
                printOrganism(grid[y][x]);
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    public void addOrganism(int x, int y, Organism organism) {
        grid[x][y].add(organism);
    }

    private void printOrganism(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext())
            System.out.print(i.next().getSymbol());
    }
}
