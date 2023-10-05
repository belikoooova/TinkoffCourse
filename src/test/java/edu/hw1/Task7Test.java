package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Однозначное число")
    void oneSigned() {
        // given
        int number = 1;

        // when
        int result = Task7.rotateRight(number, 7);

        // then
        assertThat(result).isEqualTo(1);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Сдвиг вправо")
    void rightShift() {
        // given
        int number = 8;

        // when
        int result = Task7.rotateRight(number, 1);

        // then
        assertThat(result).isEqualTo(4);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Сдвиг влево")
    void leftShift() {
        // given
        int number = 17;

        // when
        int result = Task7.rotateLeft(number, 2);

        // then
        assertThat(result).isEqualTo(6);
    }
}
