package com.github.johnblakey.bug.world;

import java.util.HashSet;
import java.util.Iterator;
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

    // TODO improve the printing for fixed width that can handle up to 3 organisms
    public void print() {
        for (int y = size - 1; y >= 0; y--) {
            System.out.print("|");
            for (int x = 0; x < size; x++) {
                // print empty space based on organism population
                for (int i = 0; i < totalOrganismTypes - grid[x][y].size(); i++) {
                    System.out.print(" ");
                }
                displayOrganisms(grid[x][y]);
                System.out.print("|");
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

    public void resetMovedGrid() {
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                resetMovedSquare(grid[x][y]);
            }
        }
    }

    public void resetMovedSquare(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            i.next().setHasMoved(false);
        }
    }

    public void moveGridOrganisms() {
        resetMovedGrid();
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                moveSquareOrganisms(grid[x][y]);
            }
        }
    }

    private boolean moveSquareOrganisms(HashSet<Organism> square) {
        Iterator<Organism> i = square.iterator();
        while (i.hasNext()) {
            if (checkSquareOrganism(i.next()))
                return true;
        }
        return false;
    }

    // TODO refactor
    private boolean checkSquareOrganism(Organism organism) {
        if (organism.getHasMoved())
            return false;

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


        Vector<SquareCoordinates> validSquares = findAdjacent(organism, xAdjacent, yAdjacent);

        // loop through adjacent squares - organism evaluates each if appropriate and move them if so
        for (SquareCoordinates pair : validSquares) {
            if (organism.moveEat(grid[pair.getX()][pair.getY()])) {
                // Grid and Organism steps to move the organism
                removeOrganism(organism);
                organism.setX(pair.getX());
                organism.setY(pair.getY());
                addOrganism(organism);
                organism.setHasMoved(true);
                return true;
            }
        }
        for (SquareCoordinates pair : validSquares) {
            if(organism.move(grid[pair.getX()][pair.getY()])) {
                // Grid and Organism steps to move the organism
                removeOrganism(organism);
                organism.setX(pair.getX());
                organism.setY(pair.getY());
                addOrganism(organism);
                organism.setHasMoved(true);
                return true;
            }
        }
        // organism didn't move b/c no valid location existed
        return false;
    }

    private Vector<SquareCoordinates> findAdjacent(Organism organism, Vector<Integer> xV, Vector<Integer> yV) {
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
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                squareOrganismsActions(grid[x][y]);
            }
        }
    }

    // TODO refactor
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
            }
        }

        // drive evaluation (TODO refactor to organisms -> grid knows too much)
        if (hasSpider && hasAnt) {
            removeOrganism(ant);
        } else if (hasAnt && hasPlant) {
            removeOrganism(plant);
        }
    }

}
