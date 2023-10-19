package edu.hw2.task2;

public class Rectangle {
    protected double width = 0;
    protected double height = 0;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(double width) {
        return new Rectangle(width, this.height);
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(this.width, height);
    }

    public double area() {
        return width * height;
    }
}
