package edu.hw3;

import edu.hw3.task4.RomanConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    private static final int FIRST_TEST_NUMBER = 2;
    private static final int SECOND_TEST_NUMBER = 12;
    private static final int THIRD_TEST_NUMBER = 16;
    private static final int BIG_TEST_NUMBER = 3764;

    @Test
    @DisplayName("1st sample test")
    void firstSampleTest() {
        // given
        int number = FIRST_TEST_NUMBER;

        // when
        var result = RomanConverter.convertToRoman(number);

        // then
        assertThat(result).isEqualTo("II");
    }

    @Test
    @DisplayName("2nd sample test")
    void secondSampleTest() {
        // given
        int number = SECOND_TEST_NUMBER;

        // when
        var result = RomanConverter.convertToRoman(number);

        // then
        assertThat(result).isEqualTo("XII");
    }

    @Test
    @DisplayName("3rd sample test")
    void thirdSampleTest() {
        // given
        int number = THIRD_TEST_NUMBER;

        // when
        var result = RomanConverter.convertToRoman(number);

        // then
        assertThat(result).isEqualTo("XVI");
    }

    @Test
    @DisplayName("4th sample test")
    void fourthSampleTest() {
        // given
        int number = BIG_TEST_NUMBER;

        // when
        var result = RomanConverter.convertToRoman(number);

        // then
        assertThat(result).isEqualTo("MMMDCCLXIV");
    }
}
