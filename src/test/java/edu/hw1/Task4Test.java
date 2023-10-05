package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        // given
        String toFix = "";

        // when
        String result = Task4.fixString(toFix);

        // then
        assertThat(result).isEmpty();
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Cтрока - null")
    void nullString() {
        // given
        String toFix = null;

        // when
        String result = Task4.fixString(toFix);

        // then
        assertThat(result).isNull();
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Cтрока четной длины")
    void oddLengthString() {
        // given
        String toFix = "abcd";

        // when
        String result = Task4.fixString(toFix);

        // then
        assertThat(result).isEqualTo("badc");
    }

    @Test
    @DisplayName("Cтрока нечетной длины")
    void evenLengthString() {
        // given
        String toFix = "abcde";

        // when
        String result = Task4.fixString(toFix);

        // then
        assertThat(result).isEqualTo("badce");
    }
}
