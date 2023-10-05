package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Число не четырехзначное (и не 1000)")
    void notFourSigned() {
        // given
        int number = 13;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Все цифры одинаковые")
    void allDigitsAreSame() {
        // given
        int number = 5555;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Первый корректный пример")
    void firstCorrect() {
        // given
        int number = 6621;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(5);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Второй корректный пример")
    void secondCorrect() {
        // given
        int number = 1234;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(3);
    }

    @SuppressWarnings("MagicNumber")
    @Test
    @DisplayName("Сразу 6174")
    void already6174() {
        // given
        int number = 6174;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isZero();
    }
}
