package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Arrays;
import static edu.hw5.task3.ParseUtlis.DAY_INDEX;
import static edu.hw5.task3.ParseUtlis.MONTH_INDEX;
import static edu.hw5.task3.ParseUtlis.YEAR_INDEX;
import static edu.hw5.task3.ParseUtlis.checkByPattern;
import static edu.hw5.task3.ParseUtlis.checkDateComponents;

public class DashParser implements DateParser {
    @Override
    public boolean canParseDate(String date) {
        if (!checkByPattern("^\\d{4}(-\\d\\d?){2}$", date)) {
            return false;
        }
        var nums = getNumbersOfDate(date);
        return checkDateComponents(nums);
    }

    @Override
    public LocalDate parseDate(String date) {
        var dateComponents = getNumbersOfDate(date);
        return LocalDate.of(dateComponents.year(), dateComponents.month(), dateComponents.day());
    }

    private DateComponents getNumbersOfDate(String dateString) {
        var nums = Arrays
            .stream(dateString.split("-"))
            .mapToInt(Integer::parseInt)
            .toArray();
        return new DateComponents(nums[DAY_INDEX], nums[MONTH_INDEX], nums[YEAR_INDEX]);
    }
}
