package edu.hw5.task3;

import java.time.LocalDate;
import static edu.hw5.task3.ParseUtlis.checkByPattern;

public class DayAgoParser implements DateParser {
    @Override
    public boolean canParseDate(String date) {
        return checkByPattern("^\\d+ days? ago", date);
    }

    @Override
    public LocalDate parseDate(String date) {
        int days = Integer.parseInt(date.split(" ")[0]);
        return LocalDate.now().minusDays(days);
    }
}
