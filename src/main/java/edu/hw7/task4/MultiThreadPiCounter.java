package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadPiCounter implements PiCounter {
    private AtomicLong circleCount = new AtomicLong(0);
    private AtomicLong totalCount = new AtomicLong(0);

    @Override
    public double getPiValue(long iterationAmount) {
        totalCount.set(iterationAmount);
        return PiUtils.MULTIPLICATOR * circleCount.get() / totalCount.get();
    }

    @Override
    public Point generatePoint() {
        Point point = new Point(generatePointCoordinate(), generatePointCoordinate());
        if (PiUtils.isInCircle(point)) {
            circleCount.getAndIncrement();
        }
        return point;
    }

    private double generatePointCoordinate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double coordinate = random.nextDouble();
        return random.nextInt() % 2 == 0 ? coordinate : coordinate * -1;
    }
}
