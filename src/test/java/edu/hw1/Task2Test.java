package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    private static final long POS_10_DIGITS_NUMBER = 1002849503;
    private static final int TEN = 10;
    private static final long ZERO = 0;
    private static final long NINE = 9;
    private static final int ONE = 1;
    private static final long NEG_7_DIGITS_NUMBER = -2849503;
    private static final int SEVEN = 7;
    @Test
    @DisplayName("Положительное 10-значное число")
    void posTenDigits() {
        // given
        long number = POS_10_DIGITS_NUMBER;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(TEN);
    }

    @Test
    @DisplayName("Ноль")
    void zero() {
        // given
        long number = ZERO;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(ONE);
    }

    @Test
    @DisplayName("Положительное 1-значное число")
    void posOneDigit() {
        // given
        long number = NINE;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(ONE);
    }

    @Test
    @DisplayName("Отрицательное 7-значное число")
    void negSevenDigits() {
        // given
        long number = NEG_7_DIGITS_NUMBER;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(SEVEN);
    }
}
