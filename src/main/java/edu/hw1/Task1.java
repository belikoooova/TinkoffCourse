package edu.hw1;

public class Task1 {
    @SuppressWarnings("MagicNumber")
    public static long minutesToSeconds(String time) {
        if (time == null || !time.matches("\\d+:\\d+")) {
            return -1;
        }
        long minutes = Long.parseLong(time.split(":")[0]);
        long seconds = Long.parseLong(time.split(":")[1]);
        if (seconds >= 60) {
            return -1;
        }
        return minutes * 60 + seconds;
    }
}
