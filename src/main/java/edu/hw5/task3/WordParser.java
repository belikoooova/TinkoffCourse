package edu.hw5.task3;

import java.time.LocalDate;

public class WordParser implements DateParser {
    private static final String TOMORROW = "tomorrow";
    private static final String YESTERDAY = "yesterday";

    @Override
    public boolean canParseDate(String date) {
        return TOMORROW.equalsIgnoreCase(date) || "today".equalsIgnoreCase(date) || YESTERDAY.equalsIgnoreCase(date);
    }

    @Override
    public LocalDate parseDate(String date) {
        if (TOMORROW.equalsIgnoreCase(date)) {
            return LocalDate.now().plusDays(1);
        }
        if (YESTERDAY.equalsIgnoreCase(date)) {
            return LocalDate.now().minusDays(1);
        }
        return LocalDate.now();
    }
}
