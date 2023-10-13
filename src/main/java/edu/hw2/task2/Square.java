package edu.hw2.task2;

public class Square extends Rectangle {
    protected Square(double width, double height) {
        super(width, height);
    }

    public Square(double side) {
        super(side, side);
    }
}
