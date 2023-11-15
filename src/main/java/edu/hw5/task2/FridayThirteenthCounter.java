package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FridayThirteenthCounter {
    private static final int LIMIT_OF_YEAR = 999999999;
    private static final int THIRTEEN = 13;
    private static final int MONTH_AMOUNT = 12;

    public static List<LocalDate> getFridaysThirteenthOfYear(int year) {
        if (Math.abs(year) > LIMIT_OF_YEAR) {
            throw new IllegalArgumentException("The allowable limit of the year exceeded.");
        }
        List<LocalDate> fridays13th = new ArrayList<>();
        for (int month = 1; month <= MONTH_AMOUNT; ++month) {
            LocalDate date = LocalDate.of(year, month, THIRTEEN);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13th.add(date);
            }
        }
        return fridays13th;
    }

    public static LocalDate getNextFridayThirteenth(LocalDate date) {
        var nextFriday13th = date;
        TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);
        do {
            nextFriday13th = nextFriday13th.with(nextFriday);
        } while (nextFriday13th.getDayOfMonth() != THIRTEEN);
        return nextFriday13th;
    }
}
