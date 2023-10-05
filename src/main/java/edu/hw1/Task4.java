package edu.hw1;

public class Task4 {
    @SuppressWarnings("MagicNumber")
    public static String fixString(String toFix) {
        if (toFix == null) {
            return null;
        }
        StringBuilder fixed = new StringBuilder();
        for (int i = 0; i < toFix.length() / 2; ++i) {
            fixed.append(toFix.charAt(2 * i + 1));
            fixed.append(toFix.charAt(2 * i));
        }
        if (toFix.length() % 2 != 0) {
            fixed.append(toFix.charAt(toFix.length() - 1));
        }
        return fixed.toString();
    }
}
