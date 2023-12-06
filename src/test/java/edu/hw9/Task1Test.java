package edu.hw9;

import edu.hw9.task1.StatsCollector;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {
    private static final double SUM = 9900;
    private static final double MEAN = 49.5;
    private static final double MIN = 0;
    private static final double MAX = 99;
    private static final double ITERATIONS_AMOUNT = 100;
    private static final int TIMEOUT = 60;

    @SneakyThrows
    @Test
    void testConcurrentPushAndGetStats() {
        // given
        StatsCollector statsCollector = new StatsCollector();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // when
        for (int i = 0; i < ITERATIONS_AMOUNT; i++) {
            int finalI = i;
            Future<?> sumFuture =
                executorService.submit(() -> statsCollector.push("sum", new double[] {finalI, finalI}));
            Future<?> meanFuture =
                executorService.submit(() -> statsCollector.push("mean", new double[] {finalI, finalI}));
            Future<?> minFuture =
                executorService.submit(() -> statsCollector.push("min", new double[] {finalI, finalI}));
            Future<?> maxFuture =
                executorService.submit(() -> statsCollector.push("max", new double[] {finalI, finalI}));

            sumFuture.get();
            meanFuture.get();
            maxFuture.get();
            minFuture.get();
        }

        executorService.shutdown();

        // then
        assertTrue(executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS));
        Map<String, Double> stats = statsCollector.getStats();
        assertNotNull(stats);
        assertEquals(SUM, stats.get("sum"));
        assertEquals(MEAN, stats.get("mean"));
        assertEquals(MIN, stats.get("min"));
        assertEquals(MAX, stats.get("max"));
    }
}
