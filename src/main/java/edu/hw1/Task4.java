package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String stringToFix) {
        if (stringToFix == null) {
            return null;
        }
        StringBuilder fixed = new StringBuilder();
        for (int i = 0; i < stringToFix.length() / 2; ++i) {
            fixed.append(stringToFix.charAt(2 * i + 1));
            fixed.append(stringToFix.charAt(2 * i));
        }
        if (stringToFix.length() % 2 != 0) {
            fixed.append(stringToFix.charAt(stringToFix.length() - 1));
        }
        return fixed.toString();
    }
}
