package edu.hw8.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HackerUtils {
    public static final char[] CHARACTERS =
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final int MAX_PASSWORD_LENGTH = 4;
    public static final String ERROR_MESSAGE = "Length of passwords must be <= 4.";
    private static final int PAIR_SIZE = 2;

    public static Map<String, String> getMappedInfo(List<String> info) {
        Map<String, String> mappedInfo = new HashMap<>();
        for (var string : info) {
            var splittedString = string.split(" ");
            if (splittedString.length != PAIR_SIZE) {
                throw new IllegalArgumentException("Incorrect args.");
            }
            mappedInfo.put(splittedString[1], splittedString[0]);
        }
        return mappedInfo;
    }
}
