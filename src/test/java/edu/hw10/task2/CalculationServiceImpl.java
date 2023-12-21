package edu.hw10.task2;

public class CalculationServiceImpl implements CalculationService {
    private static final int TIMEOUT = 1000;

    @Override
    public long expensiveOperation(long number) {
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return number * number;
    }
}
