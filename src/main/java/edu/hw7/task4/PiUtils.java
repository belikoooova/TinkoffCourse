package edu.hw7.task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PiUtils {
    private static final int PERCENT = 100;
    private static final int TIMEOUT = 60;
    public static final double MULTIPLICATOR = 4d;

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
        getPiInMultiThread(iterationsAmount, numberOfThreads);
        return System.currentTimeMillis() - time;
    }

    @SneakyThrows
    public double getPiInMultiThread(long iterationsAmount, int numberOfThreads) {
        PiCounter multiThreadPiCounter = new MultiThreadPiCounter();
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            service.submit(() -> {
                for (int j = 0; j < iterationsAmount / numberOfThreads; ++j) {
                    multiThreadPiCounter.generatePoint();
                }
            });
        }
        shutdownAndAwaitTermination(service);
        return multiThreadPiCounter.getPiValue(iterationsAmount);
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    throw new TimeoutException("Pool did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
