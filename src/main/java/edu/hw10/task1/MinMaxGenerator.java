package edu.hw10.task1;

public interface MinMaxGenerator<T> extends ValueGenerator<T> {
    T generate(int min, int max);
}
