package edu.hw7.task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PiUtils {
    public static final double MULTIPLICATOR = 4d;
    private static final int PERCENT = 100;

    public static double countInaccuracy(double countedPi) {
        return PERCENT * Math.abs(Math.PI - countedPi) / Math.PI;
    }

    public boolean isInCircle(PiCounter.Point point) {
        return Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2)) <= 1;
    }

    public long getTimeOfCalculateInSingleThread(long iterationsAmount) {
        PiCounter singleThreadPiCounter = new SingleThreadPiCounter();
        long time = System.currentTimeMillis();
        singleThreadPiCounter.getPiValue(iterationsAmount);
        return System.currentTimeMillis() - time;
    }

    @SneakyThrows
    public long getTimeOfCalculateInMultiThread(long iterationsAmount, int numberOfThreads) {
        long time = System.currentTimeMillis();
        PiCounter multiThreadPiCounter = new MultiThreadPiCounter();
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            service.submit(() -> {
                for (int j = 0; j < iterationsAmount / numberOfThreads; ++j) {
                    multiThreadPiCounter.generatePoint();
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        multiThreadPiCounter.getPiValue(iterationsAmount);
        return System.currentTimeMillis() - time;
    }
}
