package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Два двоеточия (некорректная строка)")
    void badStringFirst() {
        // given
        String time = "22:44:33";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1L);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Есть буквы (некорректная строка)")
    void badStringSecond() {
        // given
        String time = "4a4:33";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1L);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Превышение количества секунд (некорректная строка)")
    void badStringThird() {
        // given
        String time = "44:63";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1L);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Пустая строка")
    void EmptyString() {
        // given
        String time = "";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(-1L);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("< 60 минут, < 60 секунд (корректная строка)")
    void correctStringFirst() {
        // given
        String time = "44:33";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(44L * 60 + 33);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("> 60 минут, < 60 секунд (корректная строка)")
    void correctStringSecond() {
        // given
        String time = "144:33";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(144L * 60 + 33);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Ведущие нули (корректная строка)")
    void correctStringThird() {
        // given
        String time = "0044:033";

        // when
        long result = Task1.minutesToSeconds(time);

        // then
        assertThat(result).isEqualTo(44L * 60 + 33);
    }
}
