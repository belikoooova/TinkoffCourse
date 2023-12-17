package edu.project4.transformations;

import edu.project4.Point;

public class HeartTransformation implements Transformation {
    private static final double COEF = 0.5;

    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double t = Math.atan(point.x() / point.y());
        double newX = r * Math.sin(t * r);
        double newY = -1 * r * Math.cos(t * r);
        return new Point(COEF * newX, COEF * newY);
    }
}
