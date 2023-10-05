package edu.hw1;

public class Task5 {
    private Task5() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(long number) {
        String s = Long.toString(number);
        while (s.length() >= 2) {
            if (isPalindrome(s)) {
                return true;
            }
            StringBuilder newString = new StringBuilder();
            for (int i = 0; i < s.length() / 2; ++i) {
                newString.append(
                    Character.getNumericValue(s.charAt(i * 2)) + Character.getNumericValue(s.charAt(i * 2 + 1)));
            }
            if (s.length() % 2 != 0) {
                newString.append(s.charAt(s.length() - 1));
            }
            s = newString.toString();
        }
        return false;
    }

    @SuppressWarnings("MagicNumber")
    private static boolean isPalindrome(String s) {
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) {
            reversed.append(s.charAt(i));
        }
        return s.contentEquals(reversed);
    }
}
