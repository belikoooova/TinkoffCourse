package edu.hw11.task2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiplicationInterceptor {
    public static int multiply(int a, int b) {
        return a * b;
    }
}
