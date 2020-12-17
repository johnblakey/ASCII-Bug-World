package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

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
        for (int y = size - 1; y >= 0; y--) {
            System.out.print("| ");
            for (int x = 0; x < size; x++) {
                printOrganism(grid[x][y]);
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    public void addOrganism(Organism organism) {
        int x = organism.getX();
        int y = organism.getY();
        grid[x][y].add(organism);
    }

    public void removeOrganism(Organism organism) {
        int x = organism.getX();
        int y = organism.getY();
        grid[x][y].remove(organism);
    }

    private void printOrganism(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext())
            System.out.print(i.next().getSymbol());
    }

    public void moveLoop() {
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                moveOrganism(grid[x][y]);
            }
        }
    }

    private void moveOrganism(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            // send message of what organism is here
            checkSquares(i.next());
        }
    }

    // refactor
    private void checkSquares(Organism organism) {
        int x = organism.getX();
        int y = organism.getY();

        // Check the 8 squares around the central location
        Vector<Integer> xAdjacent = new Vector<>();
        xAdjacent.add(x);
        Vector<Integer> yAdjacent = new Vector<>();
        yAdjacent.add(y);
        if (x - 1 >= 0)
            xAdjacent.add(x - 1);
        if (x + 1 < size)
            xAdjacent.add(x + 1);
        if (y - 1 >= 0)
            yAdjacent.add(y - 1);
        if (y + 1 < size)
            yAdjacent.add(y + 1);

        Vector<SquareLocation> validSquares = findAdjacent(organism, xAdjacent, yAdjacent);

        // loop through validSquares having the organism evaluate if appropriate and move them if so
        boolean foundFood = false;
        for (SquareLocation pair : validSquares) {
            foundFood = organism.moveEat(grid[pair.getX()][pair.getY()]);
            if(foundFood) {
                // move the organism there
                removeOrganism(organism);
                organism.setX(pair.getX());
                organism.setY(pair.getY());
                addOrganism(organism);
            }
        }
        if (!foundFood) {
            boolean foundSquare = false;
            for (SquareLocation pair : validSquares) {
                foundSquare = organism.move(grid[pair.getX()][pair.getY()]);
                if(foundSquare) {
                    // move the organism there
                    removeOrganism(organism);
                    organism.setX(pair.getX());
                    organism.setY(pair.getY());
                    addOrganism(organism);
                }
            }
        }
        // organism didn't move
    }

    private Vector<SquareLocation> findAdjacent(Organism organism, Vector<Integer> xV, Vector<Integer> yV) {
        Vector<SquareLocation> validSquares = new Vector<>();
        for (int x : xV) {
            for (int y : yV) {
                if (x != organism.getX() || y != organism.getY()) {
                    SquareLocation temp = new SquareLocation(x, y);
                    validSquares.add(temp);
                }
            }
        }
        return validSquares;
    }

    public void evaluateLoop() {
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                evaluateSquare(grid[x][y]);
            }
        }
    }

    // Grid will evaluate square and determine what animals are eaten based on interactions
    private void evaluateSquare(HashSet<Organism> square) {
        boolean hasPlant = false;
        boolean hasAnt = false;
        boolean hasSpider = false;
        Organism plant = null;
        Organism ant = null;
        Organism spider = null;

        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            Organism next = i.next();
            if (next instanceof Plant) {
                hasPlant = true;
                plant = next;
            }
            else if (next instanceof Ant) {
                hasAnt = true;
                ant = next;
            }
            else if (next instanceof Spider) {
                hasSpider = true;
                spider = next;
            }
        }

        // drive evaluation (refactor to organisms grid know too much)
        if (hasSpider && hasAnt) {
            removeOrganism(ant);
        } else if (hasAnt && hasPlant) {
            removeOrganism(plant);
        }
    }

}
