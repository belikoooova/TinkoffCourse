package edu.hw5;

import edu.hw5.task2.FridayThirteenthCounter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    private static final int FIRST_SAMPLE_YEAR = 1925;
    private static final int FIRST_SAMPLE_COUNT = 3;
    private static final int SECOND_SAMPLE_YEAR = 2024;
    private static final int SECOND_SAMPLE_COUNT = 2;
    private static final int FEB = 2;
    private static final int MAR = 3;
    private static final int SEP = 9;
    private static final int NOV = 11;
    private static final int DEC = 12;
    private static final int ONE = 2;

    @Test
    @DisplayName("1st sample test of fridays 13th of year")
    void firstSampleTest() {
        // given
        int year = FIRST_SAMPLE_YEAR;
        int[] correctMonths = new int[] {FEB, MAR, NOV};

        // when
        var result = FridayThirteenthCounter.getFridaysThirteenthOfYear(year);

        // then
        assertThat(result.size()).isEqualTo(FIRST_SAMPLE_COUNT);
        for (int i = 0; i < FIRST_SAMPLE_COUNT; ++i) {
            assertThat(result.get(i).getMonthValue()).isEqualTo(correctMonths[i]);
        }
    }

    @Test
    @DisplayName("2nd sample test of fridays 13th of year")
    void secondSampleTest() {
        // given
        int year = SECOND_SAMPLE_YEAR;
        int[] correctMonths = new int[] {SEP, DEC};

        // when
        var result = FridayThirteenthCounter.getFridaysThirteenthOfYear(year);

        // then
        assertThat(result.size()).isEqualTo(SECOND_SAMPLE_COUNT);
        for (int i = 0; i < SECOND_SAMPLE_COUNT; ++i) {
            assertThat(result.get(i).getMonthValue()).isEqualTo(correctMonths[i]);
        }
    }

    @Test
    @DisplayName("Get next friday 13th")
    void nextFriday13thTest() {
        // given
        LocalDate date = LocalDate.of(SECOND_SAMPLE_YEAR, ONE, ONE);

        // when
        var result = FridayThirteenthCounter.getNextFridayThirteenth(date);

        // then
        assertThat(result.getMonthValue()).isEqualTo(SEP);
    }
}
