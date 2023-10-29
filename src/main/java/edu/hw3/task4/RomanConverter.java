package edu.hw3.task4;

import java.util.Comparator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class RomanConverter {
    private RomanConverter() {
    }

    private static final NavigableMap<Integer, String> ARABIAN_TO_ROMAN_DICT = new TreeMap<>(Comparator.reverseOrder());
    private static final int MAX_ROMAN_NUMBER = 3999;

    public static String convertToRoman(int number) {
        if (number > MAX_ROMAN_NUMBER) {
            throw new IllegalArgumentException("Incorrect value of number.");
        }
        fillDictionary();
        StringBuilder stringBuilder = new StringBuilder();
        int copyNumber = number;
        while (copyNumber > 0) {
            for (var key : ARABIAN_TO_ROMAN_DICT.navigableKeySet()) {
                if (copyNumber >= key) {
                    copyNumber -= key;
                    stringBuilder.append(ARABIAN_TO_ROMAN_DICT.get(key));
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("MagicNumber")
    private static void fillDictionary() {
        ARABIAN_TO_ROMAN_DICT.put(1, "I");
        ARABIAN_TO_ROMAN_DICT.put(4, "IV");
        ARABIAN_TO_ROMAN_DICT.put(5, "V");
        ARABIAN_TO_ROMAN_DICT.put(9, "IX");
        ARABIAN_TO_ROMAN_DICT.put(10, "X");
        ARABIAN_TO_ROMAN_DICT.put(40, "XL");
        ARABIAN_TO_ROMAN_DICT.put(50, "L");
        ARABIAN_TO_ROMAN_DICT.put(90, "XC");
        ARABIAN_TO_ROMAN_DICT.put(100, "C");
        ARABIAN_TO_ROMAN_DICT.put(400, "CD");
        ARABIAN_TO_ROMAN_DICT.put(500, "D");
        ARABIAN_TO_ROMAN_DICT.put(900, "CM");
        ARABIAN_TO_ROMAN_DICT.put(1000, "M");
    }
}
