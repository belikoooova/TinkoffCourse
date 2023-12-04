package edu.hw7.task4;

import java.util.Random;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SingleThreadPiCounter implements PiCounter {
    private static final Random RANDOM = new Random();

    @Override
    public double getPiValue(long iterationAmount) {
        long circleCount = 0;
        for (long i = 0; i < iterationAmount; ++i) {
            Point point = generatePoint();
            if (PiUtils.isInCircle(point)) {
                ++circleCount;
            }
        }
        return PiUtils.MULTIPLICATOR * circleCount / iterationAmount;
    }

    @Override
    public Point generatePoint() {
        return new Point(generatePointCoordinate(), generatePointCoordinate());
    }

    private double generatePointCoordinate() {
        double coordinate = RANDOM.nextDouble();
        return RANDOM.nextInt() % 2 == 0 ? coordinate : coordinate * -1;
    }
}
