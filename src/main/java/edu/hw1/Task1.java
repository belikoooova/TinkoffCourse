package edu.hw1;

public class Task1 {
    private Task1() {
    }

    private static final long NUMBER_OF_SECONDS = 60;
    private static final long ERROR_EXIT_CODE = -1;

    public static long minutesToSeconds(String time) {
        if (time == null || !time.matches("\\d+:\\d+")) {
            return ERROR_EXIT_CODE;
        }
        var splittedTime = time.split(":");
        long minutes = Long.parseLong(splittedTime[0]);
        long seconds = Long.parseLong(splittedTime[1]);
        if (seconds >= NUMBER_OF_SECONDS) {
            return ERROR_EXIT_CODE;
        }
        return minutes * NUMBER_OF_SECONDS + seconds;
    }
}
