package edu.project2;

public class Cell {
    private int row;
    private int col;
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Cell(int row, int col, Type type) {
        this.col = col;
        this.row = row;
        this.type = type;
    }

    public enum Type { WALL, PASSAGE }
}
