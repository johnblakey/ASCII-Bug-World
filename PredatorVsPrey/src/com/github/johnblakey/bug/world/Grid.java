package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Grid {
    private int size;
    private HashSet<Organism>[][] grid;
    private int totalOrganismTypes = 3;

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
//            System.out.print("|");
            for (int x = 0; x < size; x++) {
                for (int i = 0; i < totalOrganismTypes - grid[x][y].size(); i++) {
                    System.out.print(" ");
                }
                displayOrganisms(grid[x][y]);
//                System.out.print("|");
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

    private void displayOrganisms(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();

        while (i.hasNext())
            System.out.print(i.next().getSymbol());
    }

    public void resetGridOrganismsDone() {
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                resetSquareOrganismsDone(grid[x][y]);
            }
        }
    }

    public void resetSquareOrganismsDone(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();

        while (i.hasNext()) {
            i.next().setIsDone(false);
        }
    }

    public void moveGridOrganisms() {
        resetGridOrganismsDone();

        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                // TODO refactor - hacky place to add this random plant generator
                addPlantRandomlyToEmptySquare(grid[x][y], x, y);
                moveSquareOrganisms(grid[x][y]);
            }
        }
    }

    private boolean moveSquareOrganisms(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();

        while (i.hasNext()) {
            Organism organism = i.next();
            Vector<SquareCoordinates> validSquares = getValidSquare(organism);

            // TODO refactor and remove injecting grid to organism
            SquareCoordinates newSquareCoordinates = organism.getNewSquare(validSquares, this);

            if (newSquareCoordinates != null) {
                i.remove();
                moveOrganism(organism, newSquareCoordinates);
            }
        }
        return false;
    }

    private void addPlantRandomlyToEmptySquare(HashSet<Organism> square, int x, int y) {
        Iterator<Organism> i = square.iterator();
        Random random = new Random();
        int bound = 3000;

        // Empty square
        if (!i.hasNext()) {
            int randomInt = random.nextInt(bound);

            // 1 in (bound) chance of being true
            if ( bound / 2 == randomInt) {
                Plant plant = new Plant(x, y);
                addOrganism(plant);
            }
        }
    }

    private void moveOrganism(Organism organism, SquareCoordinates squareCoordinates) {
        // Grid and Organism steps to move the organism
        organism.setX(squareCoordinates.getX());
        organism.setY(squareCoordinates.getY());
        addOrganism(organism);
        organism.setIsDone(true);
    }

    public HashSet<Organism> getSquareOrganisms(SquareCoordinates squareCoordinates) {
        return grid[squareCoordinates.getX()][squareCoordinates.getY()];
    }

    private Vector<SquareCoordinates> getValidSquare(Organism organism) {
        // Validate 8 square around location is valid
        int x = organism.getX();
        int y = organism.getY();

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

        return removeStartSquare(organism, xAdjacent, yAdjacent);
    }

    private Vector<SquareCoordinates> removeStartSquare(Organism organism, Vector<Integer> xV, Vector<Integer> yV) {
        Vector<SquareCoordinates> validSquares = new Vector<>();
        for (int x : xV) {
            for (int y : yV) {
                if (x != organism.getX() || y != organism.getY()) {
                    SquareCoordinates temp = new SquareCoordinates(x, y);
                    validSquares.add(temp);
                }
            }
        }
        return validSquares;
    }

    public void gridOrganismsSquareActions() {
        resetGridOrganismsDone();

        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                squareOrganismsActions(grid[x][y]);
            }
        }
    }

    // TODO refactor - grid should not hold logic on what animals are eaten
    // Grid will evaluate square and determine what animals are eaten based on interactions
    private void squareOrganismsActions(HashSet<Organism> square) {
        boolean hasPlant = false;
        boolean hasAnt = false;
        boolean hasSpider = false;
        Organism plant = null;
        Organism ant = null;
        Organism spider = null;

        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            Organism next = i.next();

            if(next.die()) {
                i.remove();
                next = null;
            } else if(next.shouldReproduce()) {
                reproduceOrganism(next);
            }
            // TODO hacky check of null value from i.remove() usage above
            if (next != null) {
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
        }

        // Eating (TODO refactor to organisms -> grid knows too much) remove dependency with spider check
        if (hasSpider && hasAnt && spider.getEatTurnsLeft() <= 0) {
            spider.performEat();
            removeOrganism(ant);
        } else if (hasAnt && hasPlant && ant.getEatTurnsLeft() <= 0) {
            ant.performEat();
            removeOrganism(plant);
        }
    }

    // TODO refactor, too many nested ifs
    private void reproduceOrganism(Organism organism) {
        Vector<SquareCoordinates> validSquares = getValidSquare(organism);

        // loop through valid squares
        for (SquareCoordinates squareCoordinates : validSquares) {
            if(!organism.getIsDone()) {
                // Grid and Organism steps to reproduce
                if (organism.validReproduceSquare(grid[squareCoordinates.getX()][squareCoordinates.getY()])) {
                    Organism baby = organism.createOffspring(squareCoordinates);
                    addOrganism(baby);
                    // baby doesn't move the same turn it is born
                    baby.setIsDone(true);
                    // TODO hacky way to exit the loop (remove)
                    return;
                }
            }
        }
    }
}
