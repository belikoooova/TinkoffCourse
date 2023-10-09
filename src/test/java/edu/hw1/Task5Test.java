package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    private static final long ALREADY_TRUE_NUMBER = 11;
    private static final long TRUE_AFTER_ONE_STEP_NUMBER = 23336014;
    private static final long TRUE_AFTER_TWO_STEPS_NUMBER = 13001120;
    private static final long TRUE_AFTER_THREE_STEPS_NUMBER = 11211230;
    private static final long TRUE_ODD_DIGITS_NUMBER = 23615;
    private static final long FALSE_ODD_DIGITS_NUMBER = 223;
    private static final long FALSE_EVEN_DIGITS_NUMBER = 14575903;
    @Test
    @DisplayName("Сразу верно")
    void immediatelyTrue() {
        // given
        long number = ALREADY_TRUE_NUMBER;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Верно после 1 действия")
    void afterFirstStepTrue() {
        // given
        long number = TRUE_AFTER_ONE_STEP_NUMBER;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Верно после 2 действия")
    void afterSecondStepTrue() {
        // given
        long number = TRUE_AFTER_TWO_STEPS_NUMBER;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Верно после 3 действия")
    void afterThirdStepTrue() {
        // given
        long number = TRUE_AFTER_THREE_STEPS_NUMBER;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Нечетное (верно)")
    void oddTrue() {
        // given
        long number = TRUE_ODD_DIGITS_NUMBER;

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
        long number = FALSE_ODD_DIGITS_NUMBER;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Четное (неверно)")
    void evenFalse() {
        // given
        long number = FALSE_EVEN_DIGITS_NUMBER;

        // when
        boolean result = Task5.isPalindromeDescendant(number);

        // then
        assertThat(result).isFalse();
        // 14575903 -> 512143 -> 637 -> 97
    }
}
