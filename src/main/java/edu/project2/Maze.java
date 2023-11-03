package edu.project2;

public final class Maze {

    private final int height;
    private final int width;

    public Cell[][] getGrid() {
        return grid;
    }

    private final Cell[][] grid;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
    }
}
