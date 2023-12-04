package edu.project4.transformations;

import edu.project4.Point;

public class Polar implements Transformation {
    private static final double COEF = 4;

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        double newX = t / Math.PI;
        double newY = r - 1;
        return new Point(COEF * newX, newY);
    }
}
