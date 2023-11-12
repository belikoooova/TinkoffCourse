package edu.hw5.task3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ParseUtlis {
    public static final int YEAR_INDEX = 2;
    public static final int MONTH_INDEX = 1;
    public static final int DAY_INDEX = 0;

    private ParseUtlis() {
    }

    public static boolean checkDateComponents(int[] nums) {
        try {
            LocalDate result = LocalDate.of(nums[YEAR_INDEX], nums[MONTH_INDEX], nums[DAY_INDEX]);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static boolean checkByPattern(String regex, String date) {
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(date).matches()) {
            return false;
        }
        return true;
    }
}
