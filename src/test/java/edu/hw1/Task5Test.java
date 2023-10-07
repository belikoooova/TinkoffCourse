package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("MagicNumber")
public class Task5Test {
    @Test
    @DisplayName("Сразу верно")
    void immediatelyTrue() {
        // given
        long number = 11;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Верно после 1 действия")
    void afterFirstStepTrue() {
        // given
        long number = 23336014;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Верно после 2 действия")
    void afterSecondStepTrue() {
        // given
        long number = 13001120;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Верно после 3 действия")
    void afterThirdStepTrue() {
        // given
        long number = 11211230;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Нечетное (верно)")
    void oddTrue() {
        // given
        long number = 23615;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
        // 23615 -> (2 + 3)(6 + 1)5 = 575
    }

    @Test
    @DisplayName("Нечетное (неверно)")
    void oddFalse() {
        // given
        long number = 223;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Четное (неверно)")
    void evenFalse() {
        // given
        long number = 14575903;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isFalse();
        // 14575903 -> 512143 -> 637 -> 97
    }
}
