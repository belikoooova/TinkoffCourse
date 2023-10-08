package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    private static final int ONE_DIGIT_NUMBER = 1;
    private static final int RIGHT_SHIFT_NUMBER = 8;
    private static final int LEFT_SHIFT_NUMBER = 17;
    private static final int FIRST_SHIFT = 7;
    private static final int SECOND_SHIFT = 1;
    private static final int THIRD_SHIFT = 2;
    private static final int FIRST_RESULT = 1;
    private static final int SECOND_RESULT = 4;
    private static final int THIRD_RESULT = 6;
    @Test
    @DisplayName("Однозначное число")
    void oneSigned() {
        // given
        int number = ONE_DIGIT_NUMBER;

        // when
        int result = Task7.rotateRight(number, FIRST_SHIFT);

        // then
        assertThat(result).isEqualTo(FIRST_RESULT);
    }

    @Test
    @DisplayName("Сдвиг вправо")
    void rightShift() {
        // given
        int number = RIGHT_SHIFT_NUMBER;

        // when
        int result = Task7.rotateRight(number, SECOND_SHIFT);

        // then
        assertThat(result).isEqualTo(SECOND_RESULT);
    }

    @Test
    @DisplayName("Сдвиг влево")
    void leftShift() {
        // given
        int number = LEFT_SHIFT_NUMBER;

        // when
        int result = Task7.rotateLeft(number, THIRD_SHIFT);

        // then
        assertThat(result).isEqualTo(THIRD_RESULT);
    }
}
