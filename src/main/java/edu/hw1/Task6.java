package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    private static final int KAPREKAR_NUMBER = 6174;
    private static final int MIN_4_DIGIT_NUMBER = 1000;
    private static final int MAX_4_DIGIT_NUMBER = 9999;
    private static final int ERROR_EXIT_CODE = -1;
    private static final int NUMBER_OF_DIGITS = 4;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static final int MAX_RECURSION_STEP_COUNT = 7;

    public static int countK(int n) throws Exception {
        if (n == KAPREKAR_NUMBER) {
            return ZERO;
        }
        if (n < MIN_4_DIGIT_NUMBER || n > MAX_4_DIGIT_NUMBER) {
            return ERROR_EXIT_CODE;
        }
        char[] charArray = Integer.toString(n).toCharArray();
        int[] digits = new int[NUMBER_OF_DIGITS];
        for (int i = ZERO; i < NUMBER_OF_DIGITS; ++i) {
            digits[i] = Character.getNumericValue(charArray[i]);
        }
        digits = Arrays.stream(digits).sorted().toArray();
        if (digits[ZERO] == digits[THREE]) {
            return ERROR_EXIT_CODE; // Если все цифры числа одинаковые - функция не работает.
        }
        int firstNum = digits[ZERO] * MIN_4_DIGIT_NUMBER + digits[ONE] * HUNDRED + digits[TWO] * TEN + digits[THREE];
        int secondNum = digits[THREE] * MIN_4_DIGIT_NUMBER + digits[TWO] * HUNDRED + digits[ONE] * TEN + digits[ZERO];
        if (countK(Math.abs(firstNum - secondNum)) + ONE > MAX_RECURSION_STEP_COUNT) {
            throw new Exception("The number of steps exceeded");
        }
        return countK(Math.abs(firstNum - secondNum)) + ONE;
    }
}
