package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    private static final int NOT_4_DIGITS_NUMBER = 13;
    private static final int ALL_DIGITS_SAME_NUMBER = 5555;
    private static final int KAPREKAR_NUMBER = 6174;
    private static final int FIRST_CORRECT_NUMBER = 6621;
    private static final int SECOND_CORRECT_NUMBER = 1234;
    @Test
    @DisplayName("Число не четырехзначное (и не 1000)")
    void notFourSigned() throws Exception {
        // given
        int number = NOT_4_DIGITS_NUMBER;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Все цифры одинаковые")
    void allDigitsAreSame() throws Exception {
        // given
        int number = ALL_DIGITS_SAME_NUMBER;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Первый корректный пример")
    void firstCorrect() throws Exception {
        // given
        int number = FIRST_CORRECT_NUMBER;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Второй корректный пример")
    void secondCorrect() throws Exception {
        // given
        int number = SECOND_CORRECT_NUMBER;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Сразу 6174")
    void already6174() throws Exception {
        // given
        int number = KAPREKAR_NUMBER;

        // when
        int result = Task6.countK(number);

        // then
        assertThat(result).isZero();
    }
}
