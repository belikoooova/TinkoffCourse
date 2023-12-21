package edu.hw10.task1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneratorsFactory {
    public static ValueGenerator<?> getGenerator(Class<?> clazz) {
        if (clazz.equals(String.class)) {
            return new StringGenerator();
        }
        throw new UnsupportedOperationException("Generator for this class is not implemented yet");
    }
}
