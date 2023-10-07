package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("MagicNumber")
public class Task6Test {
    @Test
    @DisplayName("Число не четырехзначное (и не 1000)")
    void notFourSigned() throws Exception {
        // given
        int number = 13;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Все цифры одинаковые")
    void allDigitsAreSame() throws Exception {
        // given
        int number = 5555;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Первый корректный пример")
    void firstCorrect() throws Exception {
        // given
        int number = 6621;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Второй корректный пример")
    void secondCorrect() throws Exception {
        // given
        int number = 1234;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Сразу 6174")
    void already6174() throws Exception {
        // given
        int number = 6174;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isZero();
    }
}
