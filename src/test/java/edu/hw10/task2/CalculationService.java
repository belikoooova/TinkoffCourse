package edu.hw10.task2;

public interface CalculationService {
    @Cache(persist = true)
    long expensiveOperation(long number);
}
