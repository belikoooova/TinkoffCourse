package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("MagicNumber")
public class Task2Test {
    @Test
    @DisplayName("Положительное 10-значное число")
    void posTenDigits() {
        // given
        long number = 1002849503;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("Ноль")
    void zero() {
        // given
        long number = 0;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Положительное 1-значное число")
    void posOneDigit() {
        // given
        long number = 9;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное 7-значное число")
    void negSevenDigits() {
        // given
        long number = -2849503;

        // when
        int result = Task2.countDigit(number);

        // then
        assertThat(result).isEqualTo(7);
    }
}
