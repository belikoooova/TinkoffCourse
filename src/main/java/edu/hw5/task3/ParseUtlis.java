package edu.hw5.task3;

import java.util.regex.Pattern;

public class ParseUtlis {
    public static final int YEAR_INDEX = 2;
    public static final int MONTH_INDEX = 1;
    public static final int DAY_INDEX = 0;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int FEBRUARY = 2;
    private static final int LEAP_YEAR_FREQUENCY = 4;
    private static final int LEAP_YEAR_EXCEPTION = 100;
    private static final int LEAP_YEAR_SECOND_EXCEPTION = 400;
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private ParseUtlis() {
    }

    public static boolean checkDateComponents(DateComponents dateComponents) {
        if (dateComponents.month() < MIN_MONTH || dateComponents.month() > MAX_MONTH) {
            return false;
        }

        return dateComponents.day() >= 1
            && dateComponents.day() <= getDaysInMonth(dateComponents.month(), dateComponents.year());
    }

    private static int getDaysInMonth(int month, int year) {
        if (month == FEBRUARY && isLeapYear(year)) {
            return DAYS_IN_MONTH[month - 1] + 1;
        }
        return DAYS_IN_MONTH[month - 1];
    }

    private static boolean isLeapYear(int year) {
        return (year % LEAP_YEAR_FREQUENCY == 0)
            && (year % LEAP_YEAR_EXCEPTION != 0 || year % LEAP_YEAR_SECOND_EXCEPTION == 0);
    }

    public static boolean checkByPattern(String regex, String date) {
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(date).matches()) {
            return false;
        }
        return true;
    }
}
