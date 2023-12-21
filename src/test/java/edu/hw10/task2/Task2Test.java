package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {
    private static final int TIMEOUT = 1000;

    @Test
    void testCachedMethod() {
        // given
        CalculationService target = new CalculationServiceImpl();
        CalculationService calculationService = CacheProxy.create(CalculationService.class, target);

        // when
        long startTime = System.currentTimeMillis();
        calculationService.expensiveOperation(1);
        long duration = System.currentTimeMillis() - startTime;

        // then
        assertTrue(duration >= TIMEOUT);

        // when
        startTime = System.currentTimeMillis();
        calculationService.expensiveOperation(1);
        duration = System.currentTimeMillis() - startTime;

        // then
        assertTrue(duration < TIMEOUT);
    }
}
