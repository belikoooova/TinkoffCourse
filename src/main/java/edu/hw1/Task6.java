package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int n) {
        if (n == 6174) {
            return 0;
        }
        if (n <= 1000 || n > 10000) {
            return -1;
        }
        char[] charArray = Integer.toString(n).toCharArray();
        int[] digits = new int[4];
        for (int i = 0; i < 4; ++i) {
            digits[i] = Character.getNumericValue(charArray[i]);
        }
        digits = Arrays.stream(digits).sorted().toArray();
        if (digits[0] == digits[3]) {
            return -1; // Если все цифры числа одинаковые - функция не работает.
        }
        int firstNum = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
        int secondNum = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0];
        return countK(Math.abs(firstNum - secondNum)) + 1;
    }
}
