package edu.hw7.task4;

public interface PiCounter {
    double getPiValue(long iterationAmount);

    Point generatePoint();

    record Point(double x, double y) {
    }
}
