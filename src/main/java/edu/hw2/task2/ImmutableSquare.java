package edu.hw2.task2;

public final class ImmutableSquare extends Square {
    public ImmutableSquare(double width, double height) {
        super(width, height);
    }

    public ImmutableSquare(double side) {
        super(side, side);
    }
}
