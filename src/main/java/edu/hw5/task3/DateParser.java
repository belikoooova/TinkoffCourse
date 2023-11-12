package edu.hw5.task3;

import java.time.LocalDate;

public interface DateParser {
    boolean canParseDate(String date);

    LocalDate parseDate(String date);
}
